package com.ays.newsapp.common.util

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

object ValidationUtil {

    /**
     * Check if valid arg news
     *
     * @param str
     * @return
     */
    fun checkIfValidArgNews(str: String?): Boolean {
        return !(str.isNullOrEmpty() || str == "{country}" || str == "{language}" || str == "{source}")
    }

}