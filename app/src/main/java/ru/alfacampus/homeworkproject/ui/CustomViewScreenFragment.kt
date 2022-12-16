package ru.alfacampus.homeworkproject.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.alfacampus.homeworkproject.R
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
        }
    }.root
}
