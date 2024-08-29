package com.ays.newsapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ays.newsapp.R
import com.ays.newsapp.data.database.entity.Article
import com.ays.newsapp.ui.base.ShowError
import com.ays.newsapp.ui.components.NewsLayoutWithDelete
import com.ays.newsapp.ui.viewmodels.SharedViewModel

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Saved screen
 *
 * @param sharedViewModel
 * @param newsClicked
 * @receiver
 */
@Composable
fun SavedScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    newsClicked: (Article) -> Unit
) {

    val newsList: List<Article> by sharedViewModel.getSavedNews()
        .collectAsStateWithLifecycle(emptyList())

    if (newsList.isEmpty()) {
        ShowError(text = stringResource(R.string.no_saved_news))
    } else {
        NewsLayoutWithDelete(newsList = newsList,
            articleClicked = {
                newsClicked(it)
            }) {
            sharedViewModel.deleteArticle(it)
        }
    }

}