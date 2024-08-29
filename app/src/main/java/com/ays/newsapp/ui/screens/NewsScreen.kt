package com.ays.newsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ays.newsapp.R
import com.ays.newsapp.common.NoInternetException
import com.ays.newsapp.common.util.filterArticles
import com.ays.newsapp.data.database.entity.Article
import com.ays.newsapp.ui.base.ShowError
import com.ays.newsapp.ui.base.ShowLoading
import com.ays.newsapp.ui.base.UIState
import com.ays.newsapp.ui.components.Article
import com.ays.newsapp.ui.components.NewsLayout
import com.ays.newsapp.ui.viewmodels.NewsViewModel

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * News screen
 *
 * @param newsViewModel
 * @param newsClicked
 * @receiver
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
    newsClicked: (Article) -> Unit
) {

    val newsUiState: UIState<List<Article>> by newsViewModel.newsItem.collectAsStateWithLifecycle()

    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            newsViewModel.fetchNews()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        when (newsUiState) {
            is UIState.Loading -> {
                if (!isRefreshing)
                    ShowLoading()
            }

            is UIState.Failure -> {
                isRefreshing = false
                var errorText = stringResource(id = R.string.something_went_wrong)
                if ((newsUiState as UIState.Failure<List<Article>>).throwable is NoInternetException) {
                    errorText = stringResource(id = R.string.no_internet_available)
                }
                ShowError(
                    text = errorText,
                    retryEnabled = true
                ) {
                    newsViewModel.fetchNews()
                }
            }

            is UIState.Success -> {
                isRefreshing = false
                if ((newsUiState as UIState.Success<List<Article>>).data
                        .isEmpty()
                ) {
                    ShowError(text = stringResource(R.string.no_data_available))
                } else {
                    NewsLayout(newsList = (newsUiState as UIState.Success<List<Article>>).data) {
                        newsClicked(it)
                    }
                }
            }

            is UIState.Empty -> {
                Log.d("NewsTest","UIState.Empty")
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

/**
 * News screen paging
 *
 * @param newsViewModel
 * @param newsClicked
 * @receiver
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsScreenPaging(
    newsViewModel: NewsViewModel = hiltViewModel(),
    newsClicked: (Article) -> Unit
) {
    val pagingResponse = newsViewModel.newsItemPaging.collectAsLazyPagingItems()

    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            newsViewModel.fetchNews()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        when (pagingResponse.loadState.refresh) {
            is LoadState.Loading -> {
                if (!isRefreshing)
                    ShowLoading()
            }

            is LoadState.Error -> {
                isRefreshing = false
                var errorText = stringResource(id = R.string.something_went_wrong)
                if ((pagingResponse.loadState.refresh as LoadState.Error).error is NoInternetException) {
                    errorText = stringResource(id = R.string.no_internet_available)
                }
                ShowError(
                    text = errorText,
                    retryEnabled = true
                ) {
                    newsViewModel.fetchNews()
                }
            }

            else -> {
                isRefreshing = false
                NewsPagingAppend(pagingResponse, newsClicked)
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }

}

@Composable
private fun NewsPagingAppend(
    pagingResponse: LazyPagingItems<Article>,
    newsClicked: (Article) -> Unit,
) {
    LazyColumn {
        items(pagingResponse.itemCount) {
            Log.d("NewsTest", "Rendering item at position: $it")
            if (pagingResponse[it] != null) {
                Article(pagingResponse[it]!!) { article ->
                    newsClicked(article)
                }
            }
        }
        pagingResponse.apply {
            when (loadState.append) {
                is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                                strokeWidth = 1.dp
                            )
                        }
                    }
                }

                is LoadState.Error -> {
                    item {
                        ShowError(
                            text = stringResource(id = R.string.retry_on_pagination),
                            retryEnabled = true
                        ) {
                            pagingResponse.retry()
                        }
                    }
                }
                else -> {
                    Log.d("NewsTest", "LoadState NotLoading")
                }
            }
        }
    }
}
