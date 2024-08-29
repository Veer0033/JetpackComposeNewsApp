package com.ays.newsapp.data.database.entity

import androidx.room.ColumnInfo

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Saved source entity
 *
 * @property id
 * @property name
 * @constructor Create empty Saved source entity
 */
data class SavedSourceEntity(
    @ColumnInfo("sourceId")
    val id: String?,

    @ColumnInfo("sourceName")
    val name: String?
)