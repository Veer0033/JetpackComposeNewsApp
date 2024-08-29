package com.ays.newsapp.ui.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ays.newsapp.common.Const
import com.ays.newsapp.common.NoInternetException
import com.ays.newsapp.common.dispatcher.DispatcherProvider
import com.ays.newsapp.common.networkhelper.NetworkHelper
import com.ays.newsapp.data.database.entity.Article
import com.ays.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * News paging source
 *
 * @property newsRepository
 * @property networkHelper
 * @property dispatcherProvider
 * @constructor Create empty News paging source
 */
@Singleton
class NewsPagingSource @Inject constructor(
    private val newsRepository: NewsRepository,
    private val networkHelper: NetworkHelper,
    private val dispatcherProvider: DispatcherProvider
) : PagingSource<Int, Article>() {


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val page = params.key ?: 1
        lateinit var loadResult: LoadResult<Int, Article>

        withContext(dispatcherProvider.io) {
            kotlin.runCatching {
                if (!networkHelper.isNetworkConnected()) {
                    if (page == Const.DEFAULT_PAGE_NUM) {
                        val articles = newsRepository.getNewsFromDb()
                        loadResult = LoadResult.Page(
                            data = articles,
                            prevKey = page.minus(1),
                            nextKey = if (articles.isEmpty()) null else page.plus(1)
                        )
                    } else {
                        throw NoInternetException()
                    }
                } else {
                    val articles = newsRepository.getNews(page)

                    loadResult = LoadResult.Page(
                        data = articles,
                        prevKey = if (page == 1) null else page.minus(1),
                        nextKey = if (articles.isEmpty()) null else page.plus(1)
                    )

                }
            }.onFailure {
                loadResult = LoadResult.Error(it)
            }
        }
        return loadResult
    }
}