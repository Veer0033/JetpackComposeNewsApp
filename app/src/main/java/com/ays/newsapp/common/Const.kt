package com.ays.newsapp.common

import com.ays.newsapp.BuildConfig
import com.ays.newsapp.data.model.Country
import com.ays.newsapp.data.model.Language

object Const {

    /**
     * Search News Time Delay
     */
    const val SEARCH_NEWS_TIME_DELAY = 500L

    /**
     * Default Query Page Size
     */
    const val DEFAULT_QUERY_PAGE_SIZE = 20

    /**
     * Default Page Num
     */
    const val DEFAULT_PAGE_NUM = 1

    /**
     * Default Country
     */
    const val DEFAULT_COUNTRY = "in"

    /**
     * Default Language
     */
    const val DEFAULT_LANGUAGE = "en"

    /**
     * Default Source
     */
    const val DEFAULT_SOURCE = "abc-news"

    /**
     * Api Key
     */
    const val API_KEY = BuildConfig.API_KEY

    /**
     * Base Url
     */
    const val BASE_URL = "https://newsapi.org/v2/"

    /**
     * Db Name
     */
    const val DB_NAME = "article_db"

    /**
     * Country list
     */
    val countryList: List<Country> = listOf(
        Country("United States", "us"),
        Country("Venezuela", "ve"),
        Country("South Africa", "za")
    )

    /**
     * Language list
     */
    val languageList = listOf(
        Language("English", "en"),
        Language("Spanish", "es"),
        Language("French", "fr"),

    )

}