package com.ays.newsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ays.newsapp.data.database.entity.Article
import kotlinx.coroutines.flow.Flow

/**
 * Article dao
 *
 * @constructor Create empty Article dao
 */

@Dao
interface ArticleDao {

    /**
     * Insert all
     *
     * @param articles
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>)

    /**
     * Get all articles
     *
     * @return
     */
    @Query("SELECT * FROM cached_articles")
    fun getAllArticles(): Flow<List<Article>>

    /**
     * Delete all
     *
     */
    @Query("DELETE FROM cached_articles")
    fun deleteAll()

    /**
     * Delete all and insert all
     *
     * @param articles
     */
    @Transaction
    fun deleteAllAndInsertAll(articles: List<Article>) {
        deleteAll()
        return insertAll(articles)
    }

}