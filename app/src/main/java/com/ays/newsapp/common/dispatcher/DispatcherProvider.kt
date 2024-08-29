package com.ays.newsapp.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Dispatcher provider
 *
 * @constructor Create empty Dispatcher provider
 */
interface DispatcherProvider {

    val main: CoroutineDispatcher

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher

}