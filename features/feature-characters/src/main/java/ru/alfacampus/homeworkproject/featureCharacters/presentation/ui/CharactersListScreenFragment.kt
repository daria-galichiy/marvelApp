package ru.alfacampus.homeworkproject.featureCharacters.presentation.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.alfacampus.homeworkproject.characters.databinding.ListOfCharactersBinding
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharactersResponseEntity
import ru.alfacampus.homeworkproject.coreDi.dependencies.findFeatureExternalDeps
import ru.alfacampus.homeworkproject.coreDi.vm.ViewModelFactory
import ru.alfacampus.homeworkproject.coreNetwork.utils.Resource
import ru.alfacampus.homeworkproject.featureCharacters.presentation.adapter.CharactersAdapter
import ru.alfacampus.homeworkproject.featureCharacters.presentation.vm.CharactersComponentDepsProvider
import ru.alfacampus.homeworkproject.featureCharacters.presentation.vm.CharactersComponentViewModel
import ru.alfacampus.homeworkproject.featureCharacters.presentation.vm.CharactersViewModel
import ru.alfacampus.homeworkproject.featureCharacters.presentation.vm.SearchCharactersViewModel
import ru.alfacampus.homeworkproject.featureCharacters.utils.Constants
import javax.inject.Inject
import ru.alfacampus.homeworkproject.resources.R as mainR


class CharactersListScreenFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val charactersViewModel: CharactersViewModel by viewModels { viewModelFactory }
    private val searchViewModel: SearchCharactersViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ListOfCharactersBinding

    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var searchCharactersAdapter: CharactersAdapter

    override fun onAttach(context: Context) {
        CharactersComponentDepsProvider.charactersDeps = findFeatureExternalDeps()
        ViewModelProvider(this).get<CharactersComponentViewModel>()
            .charactersComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListOfCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charactersAdapter = CharactersAdapter(charactersViewModel)
        binding.charactersRecyclerView.adapter = charactersAdapter

        searchCharactersAdapter = CharactersAdapter(searchViewModel)
        binding.searchCharactersRecyclerView.adapter = searchCharactersAdapter

        charactersAdapter.setOnItemClickListener {
            charactersViewModel.navigateToCharactersDescription(this, it)
        }

        searchCharactersAdapter.setOnItemClickListener {
            searchViewModel.navigateToCharactersDescription(this, it)
        }

        //TODO: get rid of nesting
        binding.searchInputText.addTextChangedListener { text: Editable? ->
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        delay(Constants.SEARCH_DELAY)
                        text?.let {
                            if (it.toString().isNotEmpty()) {
                                searchViewModel.searchCharactersByNameStartsWith(it.toString(), 50)
                            }
                        }
                    }
                }
            }
        }

        binding.fab.setOnClickListener {
            //TODO: come up with implementation or delete the fab
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            charactersViewModel.charactersStateFlow.collect { response ->
                when (response) {
                    is Resource.Success -> onSuccessCharactersLoaded(response)
                    is Resource.Error -> onErrorCharactersNotLoaded(response)
                    is Resource.Loading -> onLoadingCharacters(response)
                    else -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            searchViewModel.searchCharactersStateFlow.collect { response ->
                when (response) {
                    is Resource.Success -> onSuccessCharactersFound(response)
                    is Resource.Error -> onErrorCharactersNotFound(response)
                    is Resource.Loading -> onFindingCharacters(response)
                    else -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            charactersViewModel.shareCharacterStateFlow.collect { character ->
                if (character != null)
                    onShareCharacterClicked(character, true)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            searchViewModel.shareFoundCharacterStateFlow.collect { character ->
                if (character != null)
                    onShareCharacterClicked(character)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    charactersViewModel.navigateToStartScreen(this@CharactersListScreenFragment)
                }
            })
    }

    private fun onSuccessCharactersLoaded(response: Resource<CharactersResponseEntity>) {
        Log.d("RecyclerViewScreenFragment", "Resource.Success -> Characters were loaded")
        binding.charactersGroup.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
        response.data?.let {
            charactersAdapter.submitList(it.data.results)
            binding.copyrightText.text = it.copyright
        }
    }

    private fun onErrorCharactersNotLoaded(response: Resource<CharactersResponseEntity>) {
        Log.d("RecyclerViewScreenFragment", "Resource.Error -> Characters were not loaded")
        binding.charactersGroup.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.INVISIBLE
        response.message?.let {
            Log.e("checkData", "RecyclerViewScreenFragment: error: $it")
        }
    }

    private fun onLoadingCharacters(response: Resource<CharactersResponseEntity>) {
        Log.d("RecyclerViewScreenFragment", "Resource.Loading -> Characters are loading")
        binding.charactersGroup.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
        binding.searchProgressBar.visibility = View.INVISIBLE
    }

    private fun onSuccessCharactersFound(response: Resource<CharactersResponseEntity>) {
        Log.d("RecyclerViewScreenFragment", "Resource.Success -> Characters were found be name")
        binding.searchCharactersRecyclerView.visibility = View.VISIBLE
        binding.searchProgressBar.visibility = View.INVISIBLE
        response.data?.let {
            searchCharactersAdapter.submitList(it.data.results)
            if (it.data.count == 0)
                binding.noSearchResultsTextView.visibility = View.VISIBLE
        }
        binding.nestedScrollView.fullScroll(View.FOCUS_DOWN)
        binding.searchInputText.requestFocus()
    }

    private fun onErrorCharactersNotFound(response: Resource<CharactersResponseEntity>) {
        Log.d(
            "RecyclerViewScreenFragment:",
            "Resource.Error -> Characters were not found by name. Error occurred"
        )
        binding.searchCharactersRecyclerView.visibility = View.INVISIBLE
        binding.searchProgressBar.visibility = View.INVISIBLE
        response.message?.let {
            Log.e("checkData", "RecyclerViewScreenFragment: error: $it")
        }
    }

    private fun onFindingCharacters(response: Resource<CharactersResponseEntity>) {
        Log.d("RecyclerViewScreenFragment:", "Resource.Loading -> Characters are finding by name")
        binding.noSearchResultsTextView.visibility = View.INVISIBLE
        binding.searchCharactersRecyclerView.visibility = View.INVISIBLE
        binding.searchProgressBar.visibility = View.VISIBLE
    }

    private fun onShareCharacterClicked(
        character: CharacterMarvelEntity,
        isCalledFromCharactersRecyclerView: Boolean = false
    ) {
        try {

            val bitmapImage = if (isCalledFromCharactersRecyclerView)
                charactersViewModel.characterImageBitmap
            else
                searchViewModel.foundCharacterImageBitmap

            val bitmapPath = MediaStore.Images.Media.insertImage(
                requireContext().contentResolver,
                bitmapImage,
                "Character", null
            )

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,
                    getString(mainR.string.share_character_message_title) + "\n"
                            + character.name + "\n" + character.description)
                putExtra(
                    Intent.EXTRA_STREAM,
                    Uri.parse(bitmapPath)
                )
                type = "image/*"
            }
            val shareIntent = Intent.createChooser(
                sendIntent,
                getString(mainR.string.share_character_title)
            )
            ContextCompat.startActivity(requireContext(), shareIntent, null)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                getString(mainR.string.error_on_sending_intent),
                Toast.LENGTH_SHORT
            ).show()
        } finally {
            if (isCalledFromCharactersRecyclerView)
                charactersViewModel.onCharacterShared()
            else
                searchViewModel.onFoundCharacterShared()
        }
    }
}
