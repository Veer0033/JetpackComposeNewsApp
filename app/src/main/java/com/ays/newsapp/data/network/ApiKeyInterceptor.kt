package com.ays.newsapp.data.network

import com.ays.newsapp.di.ApiKey
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Api key interceptor
 *
 * @property apiKey
 * @constructor Create empty Api key interceptor
 */
@Singleton
class ApiKeyInterceptor @Inject constructor(@ApiKey private val apiKey: String) : Interceptor {

    /**
     * Intercept
     *
     * @param chain
     * @return
     */
    @Throws(IOException::class)
    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("X-Api-Key", apiKey)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}