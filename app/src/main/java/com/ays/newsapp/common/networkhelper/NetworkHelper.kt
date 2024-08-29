package com.ays.newsapp.common.networkhelper

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Network helper
 *
 * @constructor Create empty Network helper
 */
interface NetworkHelper {
    /**
     * Is network connected
     *
     * @return
     */
    fun isNetworkConnected(): Boolean
}