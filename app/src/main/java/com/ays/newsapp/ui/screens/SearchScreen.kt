package com.ays.newsapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ays.newsapp.R
import com.ays.newsapp.common.NoInternetException
import com.ays.newsapp.common.util.filterArticles
import com.ays.newsapp.data.database.entity.Article
import com.ays.newsapp.ui.base.ShowError
import com.ays.newsapp.ui.base.ShowLoading
import com.ays.newsapp.ui.base.UIState
import com.ays.newsapp.ui.components.NewsLayout
import com.ays.newsapp.ui.viewmodels.SearchViewModel

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Search screen
 *
 * @param searchViewModel
 * @param backPressed
 * @param newsClicked
 * @receiver
 * @receiver
 */
@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    backPressed: () -> Unit,
    newsClicked: (Article) -> Unit
) {
    val searchUIState: UIState<List<Article>> by searchViewModel.searchNewsItem.collectAsStateWithLifecycle()
    val searchQuery: String by searchViewModel.query.collectAsStateWithLifecycle()
    SearchLayout(
        searchQuery = searchQuery,
        searchUIState = searchUIState,
        newsClicked = newsClicked,
        retrySearch = {
            searchViewModel.searchNews()
        },
        backPressed = backPressed
    ) {
        searchViewModel.searchNews(it)
    }
}

/**
 * Search layout
 *
 * @param searchQuery
 * @param searchUIState
 * @param newsClicked
 * @param retrySearch
 * @param backPressed
 * @param onSearchQueryChange
 * @receiver
 * @receiver
 * @receiver
 * @receiver
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLayout(
    searchQuery: String,
    searchUIState: UIState<List<Article>>,
    newsClicked: (Article) -> Unit,
    retrySearch: () -> Unit,
    backPressed: () -> Unit,
    onSearchQueryChange: (String) -> Unit
) {

    SearchBar(
        query = searchQuery,
        onQueryChange = onSearchQueryChange,
        onSearch = {},
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        active = true,
        onActiveChange = {},
        tonalElevation = 0.dp
    ) {
        when (searchUIState) {
            is UIState.Loading -> {
                ShowLoading()
            }

            is UIState.Failure -> {
                var errorText = stringResource(id = R.string.something_went_wrong)
                if (searchUIState.throwable is NoInternetException) {
                    errorText = stringResource(id = R.string.no_internet_available)
                }
                ShowError(
                    text = errorText,
                    retryEnabled = true
                ) {
                    retrySearch()
                }
            }

            is UIState.Success -> {
                if (searchUIState.data.filterArticles().isEmpty()) {
                    ShowError(text = stringResource(id = R.string.no_data_available))
                } else {
                    NewsLayout(newsList = searchUIState.data.filterArticles()) {
                        newsClicked(it)
                    }
                }
            }

            is UIState.Empty -> {

            }
        }
    }

    BackHandler {
        backPressed()
    }
}
