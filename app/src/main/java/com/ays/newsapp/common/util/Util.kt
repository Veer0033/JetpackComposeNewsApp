package com.ays.newsapp.common.util

import com.ays.newsapp.data.database.entity.Article

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Filter articles
 *
 * @return
 */
fun List<Article>.filterArticles(): List<Article> {
    return this.filterNot { article ->
        article.title.isNullOrEmpty() || article.description.isNullOrEmpty() || article.urlToImage.isNullOrEmpty()
    }
}