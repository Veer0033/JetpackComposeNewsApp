package com.ays.newsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ays.newsapp.data.database.entity.SavedArticleEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Saved article dao
 *
 * @constructor Create empty Saved article dao
 */
@Dao
interface SavedArticleDao {

    /**
     * Upsert
     *
     * @param article
     * @return
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: SavedArticleEntity): Long //update or insert

    /**
     * Get saved articles
     *
     * @return
     */
    @Query("SELECT * FROM saved_articles")
    fun getSavedArticles(): Flow<List<SavedArticleEntity>>

    /**
     * Delete article
     *
     * @param article
     */
    @Delete
    suspend fun deleteArticle(article: SavedArticleEntity)

}