package ru.alfacampus.featureCreatedCharacters.presentation.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.alfacampus.homeworkproject.resources.R as mainR
import ru.alfacampus.featureCreatedCharacters.databinding.CharacterCreationScreenBinding
import ru.alfacampus.featureCreatedCharacters.presentation.utils.ImagePicker
import ru.alfacampus.featureCreatedCharacters.presentation.vm.CreatedCharactersComponentViewModel
import ru.alfacampus.featureCreatedCharacters.presentation.vm.CreatedCharactersDepsProvider
import ru.alfacampus.featureCreatedCharacters.presentation.vm.CreatedCharactersViewModel
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CreatedCharacterEntity
import ru.alfacampus.homeworkproject.coreDi.dependencies.findFeatureExternalDeps
import ru.alfacampus.homeworkproject.coreDi.vm.ViewModelFactory
import javax.inject.Inject


class CharacterCreationFragment : Fragment() {

    private val characterArgs: CharacterCreationFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val createdCharactersViewModel: CreatedCharactersViewModel by viewModels { viewModelFactory }

    private lateinit var binding: CharacterCreationScreenBinding

    override fun onAttach(context: Context) {
        CreatedCharactersDepsProvider.createdCharactersDeps = findFeatureExternalDeps()
        ViewModelProvider(this).get<CreatedCharactersComponentViewModel>()
            .createdCharactersComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterCreationScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagePicker = ImagePicker(requireActivity().activityResultRegistry) { imageUri ->
            Log.d("pickImageFromGalleryForResult", "imageUri: " + imageUri.toString())
            setImageWithGlide(imageUri, mainR.drawable.create_character_avatar, true)
        }

        if (characterArgs.createdCharacterId != null) {
            binding.characterAvatar.visibility = View.INVISIBLE
            createdCharactersViewModel.getCreatedCharacterById(characterArgs.createdCharacterId!!.toInt())
        } else {
            binding.characterAvatar.setImageResource(mainR.drawable.create_character_avatar)
            binding.avatarProgressBar.visibility = View.INVISIBLE
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            createdCharactersViewModel.characterStateFlow.collect { character ->
                if (character != null) {
                    with(binding) {
                        characterName.setText(character.name)
                        characterDescription.setText(character.description)
                        setImageWithGlide(character.thumbnailUri.toUri(), mainR.drawable.create_character_avatar)
                    }
                    createdCharactersViewModel.resetCharacter()
                    isCharacterAvatarWasUploaded = true
                    isNewCharacter = false
                }
            }
        }

        with(binding) {
            createCharacterButton.setOnClickListener {
                if (characterName.text.isEmpty() || characterDescription.text.isEmpty())
                    showShortToast(getString(mainR.string.empty_fields_error_toast))
                else if (!isCharacterAvatarWasUploaded)
                    showShortToast(getString(mainR.string.empty_avatar_error_toast))
                else
                    createCharacter(isNewCharacter)
                //TODO: transfer the method call into vm
                    findNavController().popBackStack()
            }

            characterAvatar.setOnClickListener {
                binding.characterAvatar.visibility = View.INVISIBLE
                binding.avatarProgressBar.visibility = View.VISIBLE
                imagePicker.pickImage()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isCharacterAvatarWasUploaded = false
        characterAvatarUri = ""
        isNewCharacter = true
    }

    private fun createCharacter(isNewCharacter: Boolean) {
        with(binding) {
            val character = if (isNewCharacter) {
                CreatedCharacterEntity(
                    name = characterName.text.toString(),
                    description = characterDescription.text.toString(),
                    thumbnailUri = characterAvatarUri
                )
            } else {
                CreatedCharacterEntity(
                    id = characterArgs.createdCharacterId!!.toInt(),
                    name = characterName.text.toString(),
                    description = characterDescription.text.toString(),
                    thumbnailUri = characterAvatarUri
                )
            }
            createdCharactersViewModel.onCreateCharacterClicked(character)
        }
    }

    private fun setImageWithGlide(
        imageUri: Uri?,
        placeholderImage: Int,
        fromImagePicker: Boolean = false
    ) {
        Glide.with(this)
            .load(imageUri)
            .placeholder(placeholderImage)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    binding.avatarProgressBar.visibility = View.INVISIBLE
                    binding.characterAvatar.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    binding.avatarProgressBar.visibility = View.INVISIBLE
                    binding.characterAvatar.visibility = View.VISIBLE
                    if (fromImagePicker) {
                        isCharacterAvatarWasUploaded = true
                        characterAvatarUri = imageUri.toString()
                    } else {
                        characterAvatarUri = imageUri.toString()
                    }
                    return false
                }
            })
            .into(binding.characterAvatar)
    }

    private fun showShortToast(message: String) {
        Toast.makeText(
            activity,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        private var isCharacterAvatarWasUploaded = false
        private var characterAvatarUri = ""
        private var isNewCharacter = true
    }
}
