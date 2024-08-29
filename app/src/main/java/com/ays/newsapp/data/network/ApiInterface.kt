package com.ays.newsapp.data.network

import com.ays.newsapp.common.Const.DEFAULT_COUNTRY
import com.ays.newsapp.common.Const.DEFAULT_LANGUAGE
import com.ays.newsapp.common.Const.DEFAULT_PAGE_NUM
import com.ays.newsapp.common.Const.DEFAULT_QUERY_PAGE_SIZE
import com.ays.newsapp.common.Const.DEFAULT_SOURCE
import com.ays.newsapp.data.model.News
import com.ays.newsapp.data.model.Sources
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Api interface
 *
 * @constructor Create empty Api interface
 */
interface ApiInterface {
    /**
     * Get news
     *
     * @param country
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = DEFAULT_COUNTRY,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News

    /**
     * Get news by lang
     *
     * @param language
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("top-headlines")
    suspend fun getNewsByLang(
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News

    /**
     * Get news by source
     *
     * @param sources
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("top-headlines")
    suspend fun getNewsBySource(
        @Query("sources") sources: String = DEFAULT_SOURCE,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News


    /**
     * Search news
     *
     * @param searchQuery
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News

    /**
     * Get sources
     *
     * @return
     */
    @GET("sources")
    suspend fun getSources(): Sources

}