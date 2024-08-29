package com.ays.newsapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Saved article entity
 *
 * @property id
 * @property source
 * @property author
 * @property title
 * @property description
 * @property url
 * @property urlToImage
 * @property publishedAt
 * @property content
 * @constructor Create empty Saved article entity
 */
@Entity(
    tableName = "saved_articles",
    indices = [Index(
        value = ["title", "url"],
        unique = true
    )]
)
data class SavedArticleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Int,

    @Embedded
    val source: SavedSourceEntity,

    @ColumnInfo("author")
    val author: String?,

    @ColumnInfo("title")
    val title: String?,

    @ColumnInfo("description")
    val description: String?,

    @ColumnInfo("url")
    val url: String?,

    @ColumnInfo("urlToImage")
    val urlToImage: String?,

    @ColumnInfo("publishedAt")
    val publishedAt: String?,

    @ColumnInfo("content")
    val content: String?

)