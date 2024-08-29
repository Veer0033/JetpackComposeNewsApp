package com.ays.newsapp.common.util

import com.ays.newsapp.data.database.entity.Article
import com.ays.newsapp.data.database.entity.SavedArticleEntity
import com.ays.newsapp.data.database.entity.SavedSourceEntity
import com.ays.newsapp.data.database.entity.Source
import com.ays.newsapp.data.model.ApiArticle
import com.ays.newsapp.data.model.ApiSource

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

/**
 * Article to saved article entity
 *
 * @return
 */
fun Article.articleToSavedArticleEntity(): SavedArticleEntity {
    return SavedArticleEntity(
        id = id,
        source = source.sourceToSavedSourceEntity(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

/**
 * Saved article entity to article
 *
 * @return
 */
fun SavedArticleEntity.savedArticleEntityToArticle(): Article {
    return Article(
        id = id,
        source = source.savedSourceEntityToSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

/**
 * Source to saved source entity
 *
 * @return
 */
fun Source.sourceToSavedSourceEntity(): SavedSourceEntity {
    return SavedSourceEntity(
        id = id,
        name = name
    )
}

/**
 * Saved source entity to source
 *
 * @return
 */
fun SavedSourceEntity.savedSourceEntityToSource(): Source {
    return Source(
        id = id,
        name = name
    )
}

/**
 * Api article to article
 *
 * @return
 */
fun ApiArticle.apiArticleToArticle(): Article {
    return Article(
        source = source,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

/**
 * Api article list to article list
 *
 * @return
 */
fun List<ApiArticle>.apiArticleListToArticleList(): List<Article> {
    val list = mutableListOf<Article>()
    forEach { apiArticle ->
        list.add(apiArticle.apiArticleToArticle())
    }
    return list
}

/**
 * Api source to source
 *
 * @return
 */
fun ApiSource.apiSourceToSource(): Source {
    return Source(
        id = id,
        name = name
    )
}

/**
 * Api source list to source list
 *
 * @return
 */
fun List<ApiSource>.apiSourceListToSourceList(): List<Source> {
    val list = mutableListOf<Source>()
    forEach { apiSource ->
        list.add(apiSource.apiSourceToSource())
    }
    return list
}