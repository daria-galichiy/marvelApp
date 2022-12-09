package ru.alfacampus.homeworkproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import ru.alfacampus.homeworkproject.R
import ru.alfacampus.homeworkproject.databinding.StartScreenBinding

class StartScreenFragment : Fragment() {

    private var savedMode: String? = "auto"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedMode = savedInstanceState?.getString("SELECTED_MODE", "auto")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = StartScreenBinding.inflate(
        layoutInflater, container, false
    ).also { binding ->

        setCheckedId(savedMode, binding)
        setAppMode(savedMode)

        binding.themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == binding.lightCheckBox.id) {
                savedMode = "light"
                Toast.makeText(context, R.string.light_check_box_pushed, Toast.LENGTH_SHORT)
                    .show()
                setAppMode(savedMode)
            }

            if (checkedId == binding.darkCheckBox.id) {
                savedMode = "dark"
                Toast.makeText(context, R.string.dark_check_box_pushed, Toast.LENGTH_SHORT)
                    .show()
                setAppMode(savedMode)
            }

            if (checkedId == binding.autoCheckBox.id) {
                savedMode = "auto"
                Toast.makeText(context, R.string.auto_check_box_pushed, Toast.LENGTH_SHORT)
                    .show()
                setAppMode(savedMode)
            }
        }
    }.root

    private fun setAppMode(savedMode: String?) {
        when (savedMode) {
            "auto" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun setCheckedId(savedMode: String?, binding: StartScreenBinding) {
        when (savedMode) {
            "auto" -> binding.autoCheckBox.isChecked = true
            "light" -> binding.lightCheckBox.isChecked = true
            "dark" -> binding.darkCheckBox.isChecked = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("SELECTED_MODE", savedMode)
    }
}
