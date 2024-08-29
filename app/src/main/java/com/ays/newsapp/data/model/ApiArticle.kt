package com.ays.newsapp.data.model

import com.ays.newsapp.data.database.entity.Source
import com.google.gson.annotations.SerializedName

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Api article
 *
 * @property source
 * @property author
 * @property title
 * @property description
 * @property url
 * @property urlToImage
 * @property publishedAt
 * @property content
 * @constructor Create empty Api article
 */
data class ApiArticle(

    @SerializedName("source")
    val source: Source,

    @SerializedName("author")
    val author: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("urlToImage")
    val urlToImage: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("content")
    val content: String?

)