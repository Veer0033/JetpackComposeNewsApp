package com.ays.newsapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.ays.newsapp.data.database.entity.Source
import com.ays.newsapp.data.model.Country
import com.ays.newsapp.data.model.Language

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Source list layout
 *
 * @param sourceList
 * @param sourceClicked
 * @receiver
 */
@Composable
fun SourceListLayout(
    sourceList: List<Source>,
    sourceClicked: (Source) -> Unit
) {
    LazyColumn {
        items(sourceList) {
            SourceItem(it) { source ->
                sourceClicked(source)
            }
        }
    }
}

/**
 * Country list layout
 *
 * @param countryList
 * @param countryClicked
 * @receiver
 */
@Composable
fun CountryListLayout(
    countryList: List<Country>,
    countryClicked: (Country) -> Unit
) {
    LazyColumn {
        items(countryList) {
            CountryItem(it) { country ->
                countryClicked(country)
            }
        }
    }
}

/**
 * Language list layout
 *
 * @param languageList
 * @param languageClicked
 * @receiver
 */
@Composable
fun LanguageListLayout(
    languageList: List<Language>,
    languageClicked: (Language) -> Unit
) {
    LazyColumn {
        items(languageList) {
            LanguageItem(it) { language ->
                languageClicked(language)
            }
        }
    }
}

