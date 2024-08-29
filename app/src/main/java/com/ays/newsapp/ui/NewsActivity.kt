package com.ays.newsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.ays.newsapp.ui.base.NewsNavHost
import com.ays.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * News activity
 *
 * @constructor Create empty News activity
 */
@AndroidEntryPoint
class NewsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Surface {
                    NewsNavHost()
                }
            }
        }
    }

}