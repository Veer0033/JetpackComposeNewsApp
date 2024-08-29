package com.ays.newsapp.di

import javax.inject.Qualifier

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Api key
 *
 * @constructor Create empty Api key
 */
@Qualifier
annotation class ApiKey

/**
 * Base url
 *
 * @constructor Create empty Base url
 */
@Qualifier
annotation class BaseUrl

/**
 * Db name
 *
 * @constructor Create empty Db name
 */
@Qualifier
annotation class DbName