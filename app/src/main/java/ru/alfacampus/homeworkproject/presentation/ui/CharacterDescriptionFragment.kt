package ru.alfacampus.homeworkproject.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ru.alfacampus.homeworkproject.R
import ru.alfacampus.homeworkproject.data.dto.comics.ComicsResponse
import ru.alfacampus.homeworkproject.databinding.CharacterDescriptionBinding
import ru.alfacampus.homeworkproject.presentation.adapter.ComicsAdapter
import ru.alfacampus.homeworkproject.presentation.viewModel.ComicsViewModel
import ru.alfacampus.homeworkproject.utils.Constants
import ru.alfacampus.homeworkproject.utils.Resource

@AndroidEntryPoint
class CharacterDescriptionFragment : Fragment() {

    private val characterDescriptionArgs: CharacterDescriptionFragmentArgs by navArgs()
    private lateinit var binding: CharacterDescriptionBinding
    private val comicsViewModel: ComicsViewModel by viewModels()
    private lateinit var comicsAdapter: ComicsAdapter

    private var searchComicsJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterDescriptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comicsAdapter = ComicsAdapter()
        binding.comicsRecyclerView.adapter = comicsAdapter

        val characterMarvel = characterDescriptionArgs.characterMarvel
        with(binding) {
            Glide.with(characterAvatar.context)
                .load(
                    characterMarvel.thumbnail.path
                            + "/landscape_incredible."
                            + characterMarvel.thumbnail.extension
                )
                .error(R.drawable.agent_mobius_error)
                .into(characterAvatar)
            characterAvatar.clipToOutline = true
            characterName.text = characterMarvel.name
            characterDescription.text =
                characterMarvel.description
                    .ifEmpty { resources.getString(R.string.characters_description_not_found) }

            moreDetailsButton.setOnClickListener {
                openLinkWithCharactersDetails(characterMarvel.urls[0].url)
            }

            backToCharactersListButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        searchComicsJob?.cancel()
        searchComicsJob = MainScope().launch {
            comicsViewModel.getComicsByCharacterId(
                characterMarvel.id, 50
            )
        }

        comicsViewModel.comicsLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> onSuccessComicsFound(response)
                is Resource.Error -> onErrorComicsNotLoaded(response)
                is Resource.Loading -> onLoadingComics(response)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        searchComicsJob?.cancel()
    }

    private fun openLinkWithCharactersDetails(url: String) {
        try {
            Intent()
                .setAction(Intent.ACTION_VIEW)
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .setData(Uri.parse(takeIf { URLUtil.isValidUrl(url) }
                    ?.let {
                        url
                    } ?: Constants.MARVEL_DETAILS_URL))
                .let {
                    ContextCompat.startActivity(requireContext(), it, null)
                }
        } catch (e: Exception) {
            Toast.makeText(
                context,
                resources.getString(R.string.error_on_opening_browser),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun onSuccessComicsFound(response: Resource<ComicsResponse>) {
        Log.d("CharactersDescriptionFragment", "Resource.Success -> Comics were loaded")
        binding.comicsProgressBar.visibility = View.INVISIBLE
        binding.comicsRecyclerView.visibility = View.VISIBLE
        response.data?.let {
            comicsAdapter.submitList(it.data.results)
            binding.copyrightText.text = it.copyright
            if (it.data.count == 0)
                binding.comicsNotFoundTextView.visibility = View.VISIBLE
        }
    }

    private fun onErrorComicsNotLoaded(response: Resource<ComicsResponse>) {
        Log.d(
            "CharactersDescriptionFragment",
            "Resource.Error -> Comics were not loaded. Error occurred"
        )
        binding.comicsProgressBar.visibility = View.INVISIBLE
        binding.comicsRecyclerView.visibility = View.INVISIBLE
        response.message?.let {
            Log.e("checkData", "CharacterDescriptionFragment: error: $it")
        }
    }

    private fun onLoadingComics(response: Resource<ComicsResponse>) {
        Log.d("CharactersDescriptionFragment", "Resource.Loading -> Comics are loading")
        binding.comicsProgressBar.visibility = View.VISIBLE
        binding.comicsRecyclerView.visibility = View.INVISIBLE
        binding.comicsNotFoundTextView.visibility = View.INVISIBLE
    }
}
