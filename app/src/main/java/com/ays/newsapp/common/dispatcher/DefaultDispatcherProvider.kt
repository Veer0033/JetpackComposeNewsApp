package com.ays.newsapp.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Default dispatcher provider
 *
 * @constructor Create empty Default dispatcher provider
 */
class DefaultDispatcherProvider : DispatcherProvider {

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default

}