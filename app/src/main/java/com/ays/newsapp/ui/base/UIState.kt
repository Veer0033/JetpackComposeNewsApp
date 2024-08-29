package com.ays.newsapp.ui.base

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * U i state
 *
 * @param T
 * @constructor Create empty U i state
 */
sealed interface UIState<out T> {
    /**
     * Success
     *
     * @param T
     * @property data
     * @constructor Create empty Success
     */
    data class Success<T>(val data: T) : UIState<T>

    /**
     * Failure
     *
     * @param T
     * @property throwable
     * @property data
     * @constructor Create empty Failure
     */
    data class Failure<T>(val throwable: Throwable? = null, val data: T? = null) : UIState<T>
    object Loading : UIState<Nothing>
    object Empty : UIState<Nothing>
}
