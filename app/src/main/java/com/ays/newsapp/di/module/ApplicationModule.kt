package com.ays.newsapp.di.module

import android.app.Application
import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.ays.newsapp.common.Const
import com.ays.newsapp.common.dispatcher.DefaultDispatcherProvider
import com.ays.newsapp.common.dispatcher.DispatcherProvider
import com.ays.newsapp.common.logger.AppLogger
import com.ays.newsapp.common.logger.Logger
import com.ays.newsapp.common.networkhelper.NetworkHelper
import com.ays.newsapp.common.networkhelper.NetworkHelperImpl
import com.ays.newsapp.data.database.AppDatabaseService
import com.ays.newsapp.data.database.ArticleDatabase
import com.ays.newsapp.data.database.DatabaseService
import com.ays.newsapp.data.database.entity.Article
import com.ays.newsapp.data.network.ApiInterface
import com.ays.newsapp.data.network.ApiKeyInterceptor
import com.ays.newsapp.di.ApiKey
import com.ays.newsapp.di.BaseUrl
import com.ays.newsapp.di.DbName
import com.ays.newsapp.ui.paging.NewsPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Application module
 *
 * @constructor Create empty Application module
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    /**
     * Provide article database
     *
     * @param application
     * @param dbName
     * @return
     */
    @Singleton
    @Provides
    fun provideArticleDatabase(
        application: Application,
        @DbName dbName: String
    ): ArticleDatabase {
        return Room.databaseBuilder(
            application,
            ArticleDatabase::class.java,
            dbName
        )
            .build()
    }

    /**
     * Provide gson converter factory
     *
     * @return
     */
    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    /**
     * Provide api key
     *
     * @return
     */
    @ApiKey
    @Provides
    fun provideApiKey(): String = Const.API_KEY

    /**
     * Provide base url
     *
     * @return
     */
    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = Const.BASE_URL

    /**
     * Provide db name
     *
     * @return
     */
    @DbName
    @Provides
    fun provideDbName(): String = Const.DB_NAME

    /**
     * Provide network service
     *
     * @param baseUrl
     * @param gsonFactory
     * @param apiKeyInterceptor
     * @return
     */
    @Singleton
    @Provides
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonFactory: GsonConverterFactory,
        apiKeyInterceptor: ApiKeyInterceptor
    ): ApiInterface {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient
            .Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit
            .Builder()
            .client(client) //adding client to intercept all responses
            .baseUrl(baseUrl)
            .addConverterFactory(gsonFactory)
            .build()
            .create(ApiInterface::class.java)
    }

    /**
     * Provide logger
     *
     * @return
     */
    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

    /**
     * Provide dispatcher
     *
     * @return
     */
    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    /**
     * Provide pager
     *
     * @param newsPagingSource
     * @return
     */
    @Provides
    @Singleton
    fun providePager(
        newsPagingSource: NewsPagingSource
    ): Pager<Int, Article> {
        return Pager(
            config = PagingConfig(
                Const.DEFAULT_QUERY_PAGE_SIZE
            )
        ) {
            newsPagingSource
        }
    }

    /**
     * Provide network helper
     *
     * @param context
     * @return
     */
    @Provides
    @Singleton
    fun provideNetworkHelper(
        @ApplicationContext context: Context
    ): NetworkHelper {
        return NetworkHelperImpl(context)
    }

    /**
     * Provide database service
     *
     * @param articleDatabase
     * @return
     */
    @Provides
    @Singleton
    fun provideDatabaseService(articleDatabase: ArticleDatabase): DatabaseService {
        return AppDatabaseService(articleDatabase)
    }


}