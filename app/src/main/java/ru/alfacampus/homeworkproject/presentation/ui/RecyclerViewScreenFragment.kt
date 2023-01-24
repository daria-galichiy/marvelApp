package ru.alfacampus.homeworkproject.presentation.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.alfacampus.homeworkproject.data.dto.character.CharactersResponse
import ru.alfacampus.homeworkproject.presentation.adapter.CharactersAdapter
import ru.alfacampus.homeworkproject.databinding.ListOfCharactersBinding
import ru.alfacampus.homeworkproject.presentation.viewModel.MainViewModel
import ru.alfacampus.homeworkproject.presentation.viewModel.SearchCharactersViewModel
import ru.alfacampus.homeworkproject.utils.Constants
import ru.alfacampus.homeworkproject.utils.Resource


@AndroidEntryPoint
class RecyclerViewScreenFragment : Fragment() {

    private lateinit var binding: ListOfCharactersBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val searchViewModel: SearchCharactersViewModel by viewModels()
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var searchCharactersAdapter: CharactersAdapter

    private var searchJob: Job? = null

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

        charactersAdapter = CharactersAdapter(mainViewModel, false)
        binding.charactersRecyclerView.adapter = charactersAdapter

        searchCharactersAdapter = CharactersAdapter(searchViewModel, false)
        binding.searchCharactersRecyclerView.adapter = searchCharactersAdapter

        charactersAdapter.setOnItemClickListener {
            val direction = RecyclerViewScreenFragmentDirections.toCharacterDescriptionFragment(it)
            findNavController().navigate(direction)
        }

        searchCharactersAdapter.setOnItemClickListener {
            val direction = RecyclerViewScreenFragmentDirections.toCharacterDescriptionFragment(it)
            findNavController().navigate(direction)
        }

        binding.searchInputText.addTextChangedListener { text: Editable? ->
            searchJob?.cancel()
            searchJob = MainScope().launch {
                delay(Constants.SEARCH_DELAY)
                text?.let {
                    if (it.toString().isNotEmpty()) {
                        searchViewModel.searchCharactersByNameStartsWith(it.toString(), 50)
                    }
                }
            }
        }

        binding.fab.setOnClickListener {
            //TODO: come up with implementation or delete the fab
        }

        mainViewModel.charactersMarvelLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> onSuccessCharactersLoaded(response)
                is Resource.Error -> onErrorCharactersNotLoaded(response)
                is Resource.Loading -> onLoadingCharacters(response)
            }
        }

        searchViewModel.searchCharactersLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> onSuccessCharactersFound(response)
                is Resource.Error -> onErrorCharactersNotFound(response)
                is Resource.Loading -> onFindingCharacters(response)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        searchJob?.cancel()
    }

    private fun onSuccessCharactersLoaded(response: Resource<CharactersResponse>) {
        Log.d("RecyclerViewScreenFragment", "Resource.Success -> Characters were loaded")
        binding.charactersGroup.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
        response.data?.let {
            charactersAdapter.submitList(it.data.results)
            binding.copyrightText.text = it.copyright
        }
    }

    private fun onErrorCharactersNotLoaded(response: Resource<CharactersResponse>) {
        Log.d("RecyclerViewScreenFragment", "Resource.Error -> Characters were not loaded")
        binding.charactersGroup.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.INVISIBLE
        response.message?.let {
            Log.e("checkData", "RecyclerViewScreenFragment: error: $it")
        }
    }

    private fun onLoadingCharacters(response: Resource<CharactersResponse>) {
        Log.d("RecyclerViewScreenFragment", "Resource.Loading -> Characters are loading")
        binding.charactersGroup.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
        binding.searchProgressBar.visibility = View.INVISIBLE
    }

    private fun onSuccessCharactersFound(response: Resource<CharactersResponse>) {
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

    private fun onErrorCharactersNotFound(response: Resource<CharactersResponse>) {
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

    private fun onFindingCharacters(response: Resource<CharactersResponse>) {
        Log.d("RecyclerViewScreenFragment:", "Resource.Loading -> Characters are finding by name")
        binding.noSearchResultsTextView.visibility = View.INVISIBLE
        binding.searchCharactersRecyclerView.visibility = View.INVISIBLE
        binding.searchProgressBar.visibility = View.VISIBLE
    }
}
