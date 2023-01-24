package ru.alfacampus.homeworkproject.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.alfacampus.homeworkproject.R
import ru.alfacampus.homeworkproject.databinding.ListOfFavoriteCharactersBinding
import ru.alfacampus.homeworkproject.presentation.adapter.CharactersAdapter
import ru.alfacampus.homeworkproject.presentation.viewModel.MainViewModel


@AndroidEntryPoint
class FavoriteCharactersFragment : Fragment() {

    private lateinit var binding: ListOfFavoriteCharactersBinding
    private val favoriteCharactersViewModel: MainViewModel by viewModels()
    private lateinit var favoriteCharactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListOfFavoriteCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteCharactersAdapter = CharactersAdapter(favoriteCharactersViewModel, true)
        binding.favoriteCharactersRecyclerView.adapter = favoriteCharactersAdapter

        favoriteCharactersAdapter.setOnItemClickListener {
            val direction = FavoriteCharactersFragmentDirections.toCharacterDescriptionFragment(it)
            findNavController().navigate(direction)
        }

        favoriteCharactersViewModel.getFavoriteCharacters()
            .observe(viewLifecycleOwner) { favoriteCharacters ->
                favoriteCharactersAdapter.submitList(favoriteCharacters)
                if (favoriteCharacters.isEmpty())
                    binding.noFavoriteCharactersTextView.visibility = View.VISIBLE
                else
                    binding.noFavoriteCharactersTextView.visibility = View.INVISIBLE
            }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.UP or ItemTouchHelper.DOWN
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val characterMarvel = favoriteCharactersAdapter.currentList[position]
                favoriteCharactersViewModel.onRemoveCharacterFromFavoritesClicked(characterMarvel)
                Snackbar.make(
                    view,
                    resources.getString(R.string.character_was_deleted),
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setAction(resources.getString(R.string.cancel_deletion)) {
                        favoriteCharactersViewModel.onAddCharacterToFavoritesClicked(characterMarvel)
                    }
                }.show()
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.favoriteCharactersRecyclerView)
        }
    }
}
