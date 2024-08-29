package com.ays.newsapp.data.database

import com.ays.newsapp.data.database.entity.Article
import kotlinx.coroutines.flow.Flow

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Database service
 *
 * @constructor Create empty Database service
 */
interface DatabaseService {
    /**
     * Upsert
     *
     * @param article
     *///Saving News
    suspend fun upsert(article: Article)

    /**
     * Get saved articles
     *
     * @return
     */
    fun getSavedArticles(): Flow<List<Article>>

    /**
     * Delete article
     *
     * @param article
     */
    suspend fun deleteArticle(article: Article)

    /**
     * Get all articles
     *
     * @return
     *///Caching News
    fun getAllArticles(): Flow<List<Article>>

    /**
     * Delete all and insert all
     *
     * @param articles
     */
    fun deleteAllAndInsertAll(articles: List<Article>)
}