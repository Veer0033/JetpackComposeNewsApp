package com.ays.newsapp.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.ays.newsapp.common.Const
import com.ays.newsapp.common.NoInternetException
import com.ays.newsapp.common.dispatcher.DispatcherProvider
import com.ays.newsapp.common.logger.Logger
import com.ays.newsapp.common.networkhelper.NetworkHelper
import com.ays.newsapp.common.util.ValidationUtil.checkIfValidArgNews
import com.ays.newsapp.data.database.entity.Article
import com.ays.newsapp.data.repository.NewsRepository
import com.ays.newsapp.ui.base.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * News view model
 *
 * @property savedStateHandle
 * @property newsRepository
 * @property pager
 * @property logger
 * @property dispatcherProvider
 * @property networkHelper
 * @constructor Create empty News view model
 */
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository,
    private val pager: Pager<Int, Article>,
    private val logger: Logger,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    private var pageNum = Const.DEFAULT_PAGE_NUM
    private val _newsItem = MutableStateFlow<UIState<List<Article>>>(UIState.Empty)
    val newsItem: StateFlow<UIState<List<Article>>> = _newsItem

    private val _newsItemPaging = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val newsItemPaging: StateFlow<PagingData<Article>> = _newsItemPaging

    init {
        logger.d("Newstest","calling fetchNews from init")
        fetchNews()
    }

    /**
     * Fetch news
     *
     */
    fun fetchNews() {
        when {
            checkIfValidArgNews(savedStateHandle.get("country") as? String?) -> {
                fetchNewsByCountry(savedStateHandle.get("country"))
            }
            checkIfValidArgNews(savedStateHandle.get("language") as? String?) -> {
                fetchNewsByLanguage(savedStateHandle.get("language"))
            }
            checkIfValidArgNews(savedStateHandle.get("source") as? String?) -> {
                fetchNewsBySource(savedStateHandle.get("source"))
            }
            else -> {
                logger.d("Newstest","calling fetchNewsWithoutFilter")
                fetchNewsWithoutFilter()
            }
        }
    }

    private fun fetchNewsWithoutFilter() {
        viewModelScope.launch {
            pager.flow.cachedIn(viewModelScope)
                .map {
                    it.filter { article ->
                        article.title?.isNotEmpty() == true &&
                                article.urlToImage?.isNotEmpty() == true
                    }
                }
                .collect {
                    _newsItemPaging.value = it
                }
        }
    }

    private fun fetchNewsByCountry(countryId: String?) {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                _newsItem.emit(
                    UIState.Failure(
                        throwable = NoInternetException()
                    )
                )
                return@launch
            }
            _newsItem.emit(UIState.Loading)
            newsRepository.getNewsByCountry(
                countryId ?: Const.DEFAULT_COUNTRY,
                pageNumber = pageNum
            )
                .mapFilterCollectNews()
        }
    }

    private fun fetchNewsByLanguage(languageId: String?) {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                _newsItem.emit(
                    UIState.Failure(
                        throwable = NoInternetException()
                    )
                )
                return@launch
            }
            _newsItem.emit(UIState.Loading)
            newsRepository.getNewsByLanguage(
                languageId ?: Const.DEFAULT_LANGUAGE,
                pageNumber = pageNum
            )
                .mapFilterCollectNews()
        }
    }

    private fun fetchNewsBySource(sourceId: String?) {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                _newsItem.emit(
                    UIState.Failure(
                        throwable = NoInternetException()
                    )
                )
                return@launch
            }
            _newsItem.emit(UIState.Loading)
            newsRepository.getNewsBySource(sourceId ?: Const.DEFAULT_SOURCE, pageNumber = pageNum)
                .mapFilterCollectNews()
        }
    }

    private suspend fun Flow<List<Article>>.mapFilterCollectNews() {
        this.map { item ->
            item.apply {
                this.filter {
                    it.title?.isNotEmpty() == true &&
                            it.urlToImage?.isNotEmpty() == true
                }
            }
        }
            .flowOn(dispatcherProvider.io)
            .catch {
                _newsItem.emit(UIState.Failure(it))
            }
            .collect {
                _newsItem.emit(UIState.Success(it))
                logger.d("NewsViewModel", "Success")
            }
    }

}