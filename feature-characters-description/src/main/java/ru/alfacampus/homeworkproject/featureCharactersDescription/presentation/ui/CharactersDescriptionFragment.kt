package ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.ui

import android.content.Context
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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ru.alfacampus.homeworkproject.coreDi.dependencies.findFeatureExternalDeps
import ru.alfacampus.homeworkproject.coreDi.vm.ViewModelFactory
import ru.alfacampus.homeworkproject.coreNetwork.utils.Resource
import ru.alfacampus.homeworkproject.featureCharactersDescription.databinding.CharacterDescriptionBinding
import ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.adapter.ComicsAdapter
import ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.vm.CharactersDescriptionComponentDepsProvider
import ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.vm.CharactersDescriptionComponentViewModel
import ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.vm.ComicsViewModel
import ru.alfacampus.homeworkproject.featureCharactersDescription.utils.Constants.Companion.MARVEL_DETAILS_URL
import ru.alfacampus.homeworkproject.resources.R
import javax.inject.Inject


class CharactersDescriptionFragment : Fragment() {

    private val characterArgs: CharactersDescriptionFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val comicsViewModel: ComicsViewModel by viewModels { viewModelFactory }
    private var comicsAdapter: ComicsAdapter? = null

    private lateinit var binding: CharacterDescriptionBinding

    private var searchComicsJob: Job? = null

    override fun onAttach(context: Context) {
        CharactersDescriptionComponentDepsProvider.charactersDescriptionDeps =
            findFeatureExternalDeps()
        ViewModelProvider(this).get<CharactersDescriptionComponentViewModel>()
            .charactersDescriptionComponent.inject(this)
        super.onAttach(context)
    }

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

        with(binding) {
            Glide.with(characterAvatar.context)
                .load(
                    characterArgs.thumbnailPath
                            + "/landscape_incredible."
                            + characterArgs.thumbnailExtension
                )
                .error(R.drawable.agent_mobius_error)
                .into(characterAvatar)
            characterAvatar.clipToOutline = true
            characterName.text = characterArgs.name
            characterDescription.text =
                characterArgs.description
                    .ifEmpty { resources.getString(R.string.characters_description_not_found) }

            moreDetailsButton.setOnClickListener {
                openLinkWithCharactersDetails(characterArgs.url)
            }

            backToCharactersListButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        searchComicsJob?.cancel()
        searchComicsJob = MainScope().launch {
            comicsViewModel.getComicsByCharacterId(
                characterArgs.id.toInt(), 50
            )
        }

        //TODO: get rid of nesting
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            comicsViewModel.comicsStateFlow.collect { response ->
                when (response) {
                    is Resource.Success -> onSuccessComicsFound(response)
                    is Resource.Error -> onErrorComicsNotLoaded(response)
                    is Resource.Loading -> onLoadingComics(response)
                    else -> {}
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        comicsAdapter = null
    }

    private fun openLinkWithCharactersDetails(url: String) {
        try {
            Intent()
                .setAction(Intent.ACTION_VIEW)
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .setData(Uri.parse(takeIf { URLUtil.isValidUrl(url) }
                    ?.let {
                        url
                    } ?: MARVEL_DETAILS_URL))
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

    private fun onSuccessComicsFound(response: Resource<ru.alfacampus.homeworkproject.coreData.data.entities.comics.ComicsResponseEntity>) {
        Log.d("CharactersDescriptionFragment", "Resource.Success -> Comics were loaded")
        binding.comicsProgressBar.visibility = View.INVISIBLE
        binding.comicsRecyclerView.visibility = View.VISIBLE
        response.data?.let {
            comicsAdapter?.submitList(it.data.results)
            binding.copyrightText.text = it.copyright
            if (it.data.count == 0)
                binding.comicsNotFoundTextView.visibility = View.VISIBLE
        }
    }

    private fun onErrorComicsNotLoaded(response: Resource<ru.alfacampus.homeworkproject.coreData.data.entities.comics.ComicsResponseEntity>) {
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

    private fun onLoadingComics(response: Resource<ru.alfacampus.homeworkproject.coreData.data.entities.comics.ComicsResponseEntity>) {
        Log.d("CharactersDescriptionFragment", "Resource.Loading -> Comics are loading")
        binding.comicsProgressBar.visibility = View.VISIBLE
        binding.comicsRecyclerView.visibility = View.INVISIBLE
        binding.comicsNotFoundTextView.visibility = View.INVISIBLE
    }
}
