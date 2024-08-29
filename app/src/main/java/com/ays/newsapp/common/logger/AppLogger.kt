package com.ays.newsapp.common.logger

import android.util.Log

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * App logger
 *
 * @constructor Create empty App logger
 */
class AppLogger : Logger {
    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}