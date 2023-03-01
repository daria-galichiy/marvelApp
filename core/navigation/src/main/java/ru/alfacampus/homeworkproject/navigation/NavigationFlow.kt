package ru.alfacampus.homeworkproject.navigation

sealed class NavigationFlow {
    object CharactersFlow : NavigationFlow()
    object FavoriteCharactersFlow : NavigationFlow()
    object CreatedCharactersFlow: NavigationFlow()
}
