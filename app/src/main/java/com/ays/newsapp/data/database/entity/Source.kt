package com.ays.newsapp.data.database.entity

import androidx.room.ColumnInfo

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Source
 *
 * @property id
 * @property name
 * @constructor Create empty Source
 */
data class Source(
    @ColumnInfo("sourceId")
    val id: String?,

    @ColumnInfo("sourceName")
    val name: String?
)
