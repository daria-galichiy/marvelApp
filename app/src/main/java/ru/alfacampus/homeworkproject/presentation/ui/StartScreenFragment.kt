package ru.alfacampus.homeworkproject.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.alfacampus.homeworkproject.databinding.StartScreenBinding
import ru.alfacampus.homeworkproject.presentation.helpers.ThemeMode
import ru.alfacampus.homeworkproject.resources.R


class StartScreenFragment : Fragment() {

    private val sharedPrefs by lazy {
        this.activity?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private var savedMode = ThemeMode.AUTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themeIndex = sharedPrefs?.getInt(SELECTED_MODE, 0)
        savedMode = ThemeMode.values()[themeIndex ?: 0]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = StartScreenBinding.inflate(
        layoutInflater, container, false
    ).also { binding ->

        getCheckedId(savedMode, binding).isChecked = true
        setDefaultNightMode(getAppMode(savedMode))

        binding.themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            var changeThemeMessage = 0

            when (checkedId) {
                binding.lightCheckBox.id -> {
                    savedMode = ThemeMode.LIGHT
                    changeThemeMessage = R.string.light_check_box_pushed
                }
                binding.darkCheckBox.id -> {
                    savedMode = ThemeMode.DARK
                    changeThemeMessage = R.string.dark_check_box_pushed
                }
                binding.autoCheckBox.id -> {
                    savedMode = ThemeMode.AUTO
                    changeThemeMessage = R.string.auto_check_box_pushed
                }
            }
            setDefaultNightMode(getAppMode(savedMode))
            Toast.makeText(context, changeThemeMessage, Toast.LENGTH_SHORT)
                .show()
            sharedPrefs?.edit()?.putInt(SELECTED_MODE, savedMode.ordinal)?.apply()
        }

        binding.startScreenButton.setOnClickListener {
            val direction = StartScreenFragmentDirections.actionStartScreenFragmentToSplashNavGraph()
            findNavController().navigate(direction)
        }
    }.root

    private fun getAppMode(savedMode: ThemeMode): Int {
        return when (savedMode) {
            ThemeMode.AUTO -> MODE_NIGHT_FOLLOW_SYSTEM
            ThemeMode.LIGHT -> MODE_NIGHT_NO
            ThemeMode.DARK -> MODE_NIGHT_YES
        }
    }

    private fun getCheckedId(
        savedMode: ThemeMode,
        binding: StartScreenBinding
    ): AppCompatRadioButton {
        return when (savedMode) {
            ThemeMode.AUTO -> binding.autoCheckBox
            ThemeMode.LIGHT -> binding.lightCheckBox
            ThemeMode.DARK -> binding.darkCheckBox
        }
    }

    companion object {
        const val PREF_NAME = "DEFAULT_APP_PREFERENCE_MODE"
        const val SELECTED_MODE = "SELECTED_MODE"
    }
}
