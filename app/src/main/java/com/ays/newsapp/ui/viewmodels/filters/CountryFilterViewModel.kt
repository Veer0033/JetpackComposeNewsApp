package com.ays.newsapp.ui.viewmodels.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ays.newsapp.common.NoInternetException
import com.ays.newsapp.common.dispatcher.DispatcherProvider
import com.ays.newsapp.common.networkhelper.NetworkHelper
import com.ays.newsapp.data.model.Country
import com.ays.newsapp.data.repository.NewsRepository
import com.ays.newsapp.ui.base.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Country filter view model
 *
 * @property newsRepository
 * @property dispatcherProvider
 * @property networkHelper
 * @constructor Create empty Country filter view model
 */
@HiltViewModel
class CountryFilterViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {


    private val _countryItem = MutableStateFlow<UIState<List<Country>>>(UIState.Empty)
    val countryItem: StateFlow<UIState<List<Country>>> = _countryItem

    init {
        getCountries()
    }

    /**
     * Get countries
     *
     */
    fun getCountries() {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                _countryItem.emit(
                    UIState.Failure(
                        throwable = NoInternetException()
                    )
                )
                return@launch
            }
            _countryItem.emit(UIState.Loading)
            newsRepository.getCountries()
                .flowOn(dispatcherProvider.io)
                .catch {
                    _countryItem.emit(UIState.Failure(it))
                }
                .collect {
                    _countryItem.emit(UIState.Success(it))
                }
        }
    }
}