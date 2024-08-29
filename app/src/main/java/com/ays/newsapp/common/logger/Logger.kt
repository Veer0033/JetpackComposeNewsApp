package com.ays.newsapp.common.logger

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Logger
 *
 * @constructor Create empty Logger
 */
interface Logger {
    /**
     * D
     *
     * @param tag
     * @param msg
     */
    fun d(tag: String, msg: String)
}