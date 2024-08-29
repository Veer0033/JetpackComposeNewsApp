package com.ays.newsapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Sources
 *
 * @property status
 * @property sources
 * @constructor Create empty Sources
 */
data class Sources(
    @SerializedName("status")
    val status: String,

    @SerializedName("sources")
    val sources: List<ApiSource>
)
