package com.ays.newsapp.data.repository

import android.util.Log
import com.ays.newsapp.common.Const
import com.ays.newsapp.common.Const.DEFAULT_PAGE_NUM
import com.ays.newsapp.common.util.apiArticleListToArticleList
import com.ays.newsapp.common.util.apiSourceListToSourceList
import com.ays.newsapp.data.database.DatabaseService
import com.ays.newsapp.data.database.entity.Article
import com.ays.newsapp.data.database.entity.Source
import com.ays.newsapp.data.model.Country
import com.ays.newsapp.data.model.Language
import com.ays.newsapp.data.network.ApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * News repository
 *
 * @property database
 * @property network
 * @constructor Create empty News repository
 */
@Singleton
class NewsRepository @Inject constructor(
    private val database: DatabaseService,
    private val network: ApiInterface
) {

    /**
     * Get news
     *
     * @param pageNumber
     * @return
     */
    suspend fun getNews(pageNumber: Int = DEFAULT_PAGE_NUM): List<Article> {
        val articles = network.getNews(
            pageNum = pageNumber
        ).articles.apiArticleListToArticleList()
        return if (pageNumber == DEFAULT_PAGE_NUM) {
            database.deleteAllAndInsertAll(articles)
            database.getAllArticles().first()
        } else {
            articles
        }
    }

    /**
     * Get news test
     *
     * @param pageNumber
     * @return
     */
    suspend fun getNewsTest(pageNumber: Int = DEFAULT_PAGE_NUM): Pair<List<Article>, Int> {
        val news = network.getNews( pageNum = pageNumber)
        val articles = news.articles.apiArticleListToArticleList()

        return if (pageNumber == DEFAULT_PAGE_NUM) {
            database.deleteAllAndInsertAll(articles)
            val cachedArticles = database.getAllArticles().first()
            Pair(cachedArticles, news.totalResults)
        } else {
            Pair(articles, news.totalResults)
        }

    }


    /**
     * Get news from db
     *
     * @return
     */
    suspend fun getNewsFromDb(): List<Article> {
        return database.getAllArticles().first()
    }


    /**
     * Get news by country
     *
     * @param countryCode
     * @param pageNumber
     * @return
     */
    fun getNewsByCountry(
        countryCode: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> = flow {
        emit(
            network.getNews(
                countryCode,
                pageNum = pageNumber
            ).articles.apiArticleListToArticleList()
        )
    }

    /**
     * Get news by language
     *
     * @param languageCode
     * @param pageNumber
     * @return
     */
    fun getNewsByLanguage(
        languageCode: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> = flow {
        emit(
            network.getNewsByLang(
                languageCode,
                pageNum = pageNumber
            ).articles.apiArticleListToArticleList()
        )
    }

    /**
     * Get news by source
     *
     * @param sourceCode
     * @param pageNumber
     * @return
     */
    fun getNewsBySource(
        sourceCode: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> = flow {
        emit(
            network.getNewsBySource(
                sourceCode,
                pageNum = pageNumber
            ).articles.apiArticleListToArticleList()
        )
    }

    /**
     * Search news
     *
     * @param searchQuery
     * @param pageNumber
     * @return
     */
    fun searchNews(
        searchQuery: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> =
        flow {
            emit(
                network.searchNews(searchQuery, pageNumber).articles.apiArticleListToArticleList()
            )
        }

    /**
     * Upsert
     *
     * @param article
     */
    suspend fun upsert(article: Article) {
        database.upsert(article)
    }

    /**
     * Get saved news
     *
     * @return
     */
    fun getSavedNews(): Flow<List<Article>> = database.getSavedArticles()

    /**
     * Delete article
     *
     * @param article
     */
    suspend fun deleteArticle(article: Article) = database.deleteArticle(article)

    /**
     * Get sources
     *
     * @return
     */
    fun getSources(): Flow<List<Source>> = flow {
        emit(
            network.getSources().sources.apiSourceListToSourceList()
        )
    }

    /**
     * Get countries
     *
     * @return
     */
    fun getCountries(): Flow<List<Country>> = flow {
        emit(Const.countryList)
    }

    /**
     * Get languages
     *
     * @return
     */
    fun getLanguages(): Flow<List<Language>> = flow {
        emit(Const.languageList)
    }
}