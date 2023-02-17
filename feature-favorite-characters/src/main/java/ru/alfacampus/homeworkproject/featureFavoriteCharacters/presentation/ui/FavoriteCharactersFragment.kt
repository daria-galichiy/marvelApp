package ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.coreDi.dependencies.findFeatureExternalDeps
import ru.alfacampus.homeworkproject.coreDi.vm.ViewModelFactory
import ru.alfacampus.homeworkproject.resources.R
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.adapter.FavoriteCharactersAdapter
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.databinding.ListOfFavoriteCharactersBinding
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.vm.FavoriteCharactersComponentViewModel
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.vm.FavoriteCharactersDepsProvider
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.vm.FavoriteCharactersViewModel
import javax.inject.Inject


class FavoriteCharactersFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val favoriteCharactersViewModel: FavoriteCharactersViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ListOfFavoriteCharactersBinding

    private lateinit var favoriteCharactersAdapter: FavoriteCharactersAdapter

    override fun onAttach(context: Context) {
        FavoriteCharactersDepsProvider.favoriteCharactersDeps = findFeatureExternalDeps()
        ViewModelProvider(this).get<FavoriteCharactersComponentViewModel>()
            .favoriteCharactersComponent.inject(this)
        super.onAttach(context)
    }

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

        favoriteCharactersAdapter = FavoriteCharactersAdapter()

        binding.favoriteCharactersRecyclerView.adapter = favoriteCharactersAdapter

        //TODO: transfer the processing of navigation events to the vm
        favoriteCharactersAdapter.setOnItemClickListener {
            navigateToCharactersDescription(it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            favoriteCharactersViewModel.getFavoriteCharacters().collect() { favoriteCharacters ->
                favoriteCharactersAdapter.submitList(favoriteCharacters)
                if (favoriteCharacters.isEmpty())
                    binding.noFavoriteCharactersTextView.visibility = View.VISIBLE
                else
                    binding.noFavoriteCharactersTextView.visibility = View.INVISIBLE
            }
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
                favoriteCharactersViewModel.onDeleteCharacterFromFavoritesClicked(characterMarvel)
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

    private fun navigateToCharactersDescription(character: CharacterMarvelEntity) {
        val uri = Uri.parse(
            "homeworkproject://CharacterDescription/characterDescriptionArgs?id=${character.id}" +
                    "&name=${character.name}&description=${character.description}&thumbnailPath=${character.thumbnail.path}" +
                    "&thumbnailExtension=${character.thumbnail.extension}&url=${character.urls[0].url}"
        )
        findNavController().navigate(uri)
    }
}
