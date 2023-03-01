package ru.alfacampus.featureCreatedCharacters.presentation.utils

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts

class ImagePicker(
    private val activityResultRegistry: ActivityResultRegistry,
    private val callback: (imageUri: Uri?) -> Unit
) {

    private val pickImageFromGalleryForResult: ActivityResultLauncher<String> =
        activityResultRegistry.register(
            REGISTRY_KEY,
            ActivityResultContracts.GetContent(),
            callback
        )

    fun pickImage() {
        pickImageFromGalleryForResult.launch("image/*")
    }

    private companion object {
        private const val REGISTRY_KEY = "ImagePicker"
    }
}
