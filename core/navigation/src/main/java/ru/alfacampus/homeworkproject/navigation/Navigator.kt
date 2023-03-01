package ru.alfacampus.homeworkproject.navigation

import androidx.navigation.NavController
import javax.inject.Inject

class Navigator @Inject constructor(){
    lateinit var navController: NavController

    // navigate on main thread or nav component crashes sometimes
    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        is NavigationFlow.CharactersFlow -> navController.navigate(MainNavGraphDirections.actionGlobalCharactersFlow())
        is NavigationFlow.FavoriteCharactersFlow -> navController.navigate(MainNavGraphDirections.actionGlobalFavoriteCharactersFlow())
        is NavigationFlow.CreatedCharactersFlow -> navController.navigate(MainNavGraphDirections.actionGlobalCreatedCharactersFlow())
    }
}
