package ru.alfacampus.homeworkproject.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.alfacampus.homeworkproject.presentation.adapter.CharactersAdapter
import ru.alfacampus.homeworkproject.databinding.ListOfCharactersBinding
import ru.alfacampus.homeworkproject.presentation.viewModel.CharactersViewModel
import ru.alfacampus.homeworkproject.presentation.viewModel.MainViewModel


@AndroidEntryPoint
class RecyclerViewScreenFragment : Fragment() {

    private val viewModel: CharactersViewModel by viewModels()
    private val testViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ListOfCharactersBinding.inflate(
        layoutInflater, container, false
    ).also { binding ->

        val recycler = binding.charactersRecyclerView
        val adapter = CharactersAdapter(viewModel)
        recycler.adapter = adapter

        recycler.adapter?.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver(){
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                recycler.scrollToPosition(0)
            }
        })

        viewModel.data.observe(viewLifecycleOwner) { characters ->
            adapter.submitList(characters)
        }

        binding.fab.setOnClickListener {
            viewModel.onAddClicked()
        }
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testViewModel.getCharacters()
    }
}
