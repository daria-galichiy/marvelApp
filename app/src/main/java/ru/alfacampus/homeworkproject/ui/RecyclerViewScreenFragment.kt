package ru.alfacampus.homeworkproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import ru.alfacampus.homeworkproject.adapter.CharactersAdapter
import ru.alfacampus.homeworkproject.databinding.ListOfCharactersBinding
import ru.alfacampus.homeworkproject.viewModel.CharactersViewModel


class RecyclerViewScreenFragment : Fragment() {

    private val viewModel: CharactersViewModel by viewModels()

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
}
