package com.ays.newsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ays.newsapp.R
import com.ays.newsapp.data.database.entity.Source
import com.ays.newsapp.data.model.Country
import com.ays.newsapp.data.model.Language

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Country item
 *
 * @param country
 * @param onItemClick
 * @receiver
 */
@Composable
fun CountryItem(
    country: Country,
    onItemClick: (Country) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(4.dp)
        .clickable {
            onItemClick(country)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = country.name)
        }
    }
}

/**
 * Language item
 *
 * @param language
 * @param onItemClick
 * @receiver
 */
@Composable
fun LanguageItem(
    language: Language,
    onItemClick: (Language) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(4.dp)
        .clickable {
            onItemClick(language)
        }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = language.name)
        }
    }
}

/**
 * Source item
 *
 * @param source
 * @param onItemClick
 * @receiver
 */
@Composable
fun SourceItem(
    source: Source,
    onItemClick: (Source) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(4.dp)
        .clickable {
            onItemClick(source)
        }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = source.name ?: stringResource(R.string.unknown))
        }
    }
}
