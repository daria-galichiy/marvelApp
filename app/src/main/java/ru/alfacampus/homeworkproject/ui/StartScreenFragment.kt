package ru.alfacampus.homeworkproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.alfacampus.homeworkproject.databinding.StartScreenBinding

class StartScreenFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = StartScreenBinding.inflate(layoutInflater, container, false).root
}
