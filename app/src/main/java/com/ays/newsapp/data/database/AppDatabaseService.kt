package com.ays.newsapp.data.database

import com.ays.newsapp.common.util.articleToSavedArticleEntity
import com.ays.newsapp.common.util.savedArticleEntityToArticle
import com.ays.newsapp.data.database.entity.Article
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * App database service
 *
 * @property articleDatabase
 * @constructor Create empty App database service
 */
class AppDatabaseService(
    private val articleDatabase: ArticleDatabase
) : DatabaseService {
    /**
     * Upsert
     *
     * @param article
     */
    override suspend fun upsert(article: Article) {
        articleDatabase.getSavedArticleDao().upsert(article.articleToSavedArticleEntity())
    }

    /**
     * Get saved articles
     *
     * @return
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDatabase.getSavedArticleDao().getSavedArticles().flatMapConcat {
            flow {
                val list = mutableListOf<Article>()
                it.forEach { savedArticleEntity ->
                    list.add(savedArticleEntity.savedArticleEntityToArticle())
                }
                emit(list)
            }
        }
    }

    /**
     * Delete article
     *
     * @param article
     */
    override suspend fun deleteArticle(article: Article) {
        articleDatabase.getSavedArticleDao().deleteArticle(article.articleToSavedArticleEntity())
    }

    /**
     * Get all articles
     *
     * @return
     */
    override fun getAllArticles(): Flow<List<Article>> {
        return articleDatabase.getArticleDao().getAllArticles()
    }

    /**
     * Delete all and insert all
     *
     * @param articles
     */
    override fun deleteAllAndInsertAll(articles: List<Article>) {
        articleDatabase.getArticleDao().deleteAllAndInsertAll(articles)
    }


}