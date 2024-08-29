package com.ays.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ays.newsapp.common.dispatcher.DispatcherProvider
import com.ays.newsapp.data.database.entity.Article
import com.ays.newsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Shared view model
 *
 * @property newsRepository
 * @property dispatcherProvider
 * @constructor Create empty Shared view model
 */
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    /**
     * Save article
     *
     * @param article
     */
    fun saveArticle(article: Article) = viewModelScope.launch(dispatcherProvider.io) {
        newsRepository.upsert(article)
    }

    /**
     * Delete article
     *
     * @param article
     */
    fun deleteArticle(article: Article) = viewModelScope.launch(dispatcherProvider.io) {
        newsRepository.deleteArticle(article)
    }

    /**
     * Get saved news
     *
     */
    fun getSavedNews() = newsRepository.getSavedNews()

}

