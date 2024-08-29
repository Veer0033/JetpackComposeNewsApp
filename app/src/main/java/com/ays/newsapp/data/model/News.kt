package com.ays.newsapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * News
 *
 * @property status
 * @property totalResults
 * @property articles
 * @constructor Create empty News
 */
data class News(
    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    val articles: List<ApiArticle>
)