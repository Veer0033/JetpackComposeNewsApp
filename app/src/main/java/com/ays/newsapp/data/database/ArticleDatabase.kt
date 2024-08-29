package com.ays.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ays.newsapp.data.database.dao.ArticleDao
import com.ays.newsapp.data.database.dao.SavedArticleDao
import com.ays.newsapp.data.database.entity.Article
import com.ays.newsapp.data.database.entity.SavedArticleEntity

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Article database
 *
 * @constructor Create empty Article database
 */
@Database(entities = [SavedArticleEntity::class, Article::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {

    /**
     * Get saved article dao
     *
     * @return
     */
    abstract fun getSavedArticleDao(): SavedArticleDao

    /**
     * Get article dao
     *
     * @return
     */
    abstract fun getArticleDao(): ArticleDao

}