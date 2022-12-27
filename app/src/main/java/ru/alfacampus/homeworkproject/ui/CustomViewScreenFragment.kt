package ru.alfacampus.homeworkproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.alfacampus.homeworkproject.customviews.TrianglesView
import ru.alfacampus.homeworkproject.databinding.CustomViewScreenBinding
import ru.alfacampus.homeworkproject.helpers.ShaderType

class CustomViewScreenFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = CustomViewScreenBinding.inflate(
        layoutInflater, container, false
    ).also { binding ->
        binding.trianglesView.setOnClickListener {
            (it as TrianglesView).setShaderType(ShaderType.MarvelCharacters)
            val direction = CustomViewScreenFragmentDirections.toRecyclerViewScreenFragment()
            findNavController().navigate(direction)
        }
    }.root
}
