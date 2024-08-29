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
 * Article
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
 * @constructor Create empty Article
 */
@Entity(
    tableName = "cached_articles",
    indices = [Index(
        value = ["title", "url"],
        unique = true
    )]
)
data class Article(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Int = 0,

    @Embedded
    val source: Source,

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