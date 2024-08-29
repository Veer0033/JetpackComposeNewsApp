package com.ays.newsapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Api source
 *
 * @property id
 * @property name
 * @constructor Create empty Api source
 */
data class ApiSource(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?
)
