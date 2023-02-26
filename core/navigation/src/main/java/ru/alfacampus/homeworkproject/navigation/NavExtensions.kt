package ru.alfacampus.homeworkproject.navigation

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity

fun buildDeepLink(destination: DeepLinkDestination) =
    NavDeepLinkRequest.Builder
        .fromUri(destination.address.toUri())
        .build()

fun NavController.deepLinkNavigateTo(
    deepLinkDestination: DeepLinkDestination,
    popUpTo: Boolean = false
) {
    val builder = NavOptions.Builder()

    if (popUpTo) {
        builder.setPopUpTo(graph.startDestinationId, true)
    }

    navigate(
        buildDeepLink(deepLinkDestination),
        builder.build()
    )
}

sealed class DeepLinkDestination(val address: String) {
    class StartScreenDestination(): DeepLinkDestination("homeworkproject://StartScreen")
    class CharacterDescriptionDestination(character: CharacterMarvelEntity)
        : DeepLinkDestination(
        "homeworkproject://CharacterDescription/characterDescriptionArgs?id=${character.id}" +
                "&name=${character.name}&description=${character.description}&thumbnailPath=${character.thumbnail.path}" +
                "&thumbnailExtension=${character.thumbnail.extension}&url=${character.urls[0].url}"

    )
}
