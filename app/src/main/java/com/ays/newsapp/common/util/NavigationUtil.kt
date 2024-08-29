package com.ays.newsapp.common.util

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.ays.newsapp.ui.base.Route

/**
 * @author Omkar Veer
 * Created date : 28/08/24
 */

object NavigationUtil {

    /**
     * Navigate single top to
     *
     * @param route
     * @param navController
     */
    fun navigateSingleTopTo(
        route: String,
        navController: NavHostController
    ) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }

    /**
     * Navigate to country screen
     *
     * @param countryId
     * @param navController
     */
    fun navigateToCountryScreen(
        countryId: String,
        navController: NavHostController
    ) {
        navController.navigate(Route.TopNews.routeWithoutArgs + "/${countryId}/{language}/{source}") {
            launchSingleTop = true
        }
    }

    /**
     * Navigate to language screen
     *
     * @param languageId
     * @param navController
     */
    fun navigateToLanguageScreen(
        languageId: String,
        navController: NavHostController
    ) {
        navController.navigate(Route.TopNews.routeWithoutArgs + "/{country}/${languageId}/{source}") {
            launchSingleTop = true
        }
    }

    /**
     * Navigate to source screen
     *
     * @param sourceId
     * @param navController
     */
    fun navigateToSourceScreen(
        sourceId: String,
        navController: NavHostController
    ) {
        navController.navigate(Route.TopNews.routeWithoutArgs + "/{country}/{language}/${sourceId}") {
            launchSingleTop = true
        }
    }

}