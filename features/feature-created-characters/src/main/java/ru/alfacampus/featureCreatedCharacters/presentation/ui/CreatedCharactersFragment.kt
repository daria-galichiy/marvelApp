package ru.alfacampus.featureCreatedCharacters.presentation.ui

import android.content.Context
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
import ru.alfacampus.featureCreatedCharacters.databinding.ListOfCreatedCharactersBinding
import ru.alfacampus.featureCreatedCharacters.presentation.adapter.CreatedCharactersAdapter
import ru.alfacampus.featureCreatedCharacters.presentation.vm.CreatedCharactersComponentViewModel
import ru.alfacampus.featureCreatedCharacters.presentation.vm.CreatedCharactersDepsProvider
import ru.alfacampus.featureCreatedCharacters.presentation.vm.CreatedCharactersViewModel
import ru.alfacampus.homeworkproject.coreDi.dependencies.findFeatureExternalDeps
import ru.alfacampus.homeworkproject.coreDi.vm.ViewModelFactory
import javax.inject.Inject


class CreatedCharactersFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val createdCharactersViewModel: CreatedCharactersViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ListOfCreatedCharactersBinding

    private lateinit var createdCharactersAdapter: CreatedCharactersAdapter

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
        binding = ListOfCreatedCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createdCharactersAdapter = CreatedCharactersAdapter()

        binding.createdCharactersRecyclerView.adapter = createdCharactersAdapter

        //TODO: transfer navigation methods calls into vm
        createdCharactersAdapter.setOnItemClickListener {
            val direction =
                CreatedCharactersFragmentDirections.toCharacterCreationFragment(createdCharacterId = it.id!!.toString())
            findNavController().navigate(direction)
        }

        binding.fab.setOnClickListener {
            val direction = CreatedCharactersFragmentDirections.toCharacterCreationFragment()
            findNavController().navigate(direction)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            createdCharactersViewModel.getCreatedCharacters().collect() { createdCharacters ->
                createdCharactersAdapter.submitList(createdCharacters)
                binding.noCreatedCharactersTextView.visibility =
                    if (createdCharacters.isEmpty()) View.VISIBLE else View.INVISIBLE
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
                val createdCharacter = createdCharactersAdapter.currentList[position]
                createdCharactersViewModel.onDeleteCreatedCharacterClicked(createdCharacter)
                Snackbar.make(
                    view,
                    resources.getString(ru.alfacampus.homeworkproject.resources.R.string.created_character_was_deleted),
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setAction(resources.getString(ru.alfacampus.homeworkproject.resources.R.string.cancel_deletion)) {
                        createdCharactersViewModel.onCreateCharacterClicked(createdCharacter)
                    }
                }.show()
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.createdCharactersRecyclerView)
        }
    }
}
