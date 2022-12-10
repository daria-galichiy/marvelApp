package ru.alfacampus.homeworkproject.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import ru.alfacampus.homeworkproject.R
import ru.alfacampus.homeworkproject.databinding.StartScreenBinding
import ru.alfacampus.homeworkproject.helpers.ThemeMode


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

        setCheckedId(savedMode, binding)
        setAppMode(savedMode)

        binding.themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == binding.lightCheckBox.id) {
                savedMode = ThemeMode.LIGHT
                Toast.makeText(context, R.string.light_check_box_pushed, Toast.LENGTH_SHORT)
                    .show()
                setAppMode(savedMode)
            }

            if (checkedId == binding.darkCheckBox.id) {
                savedMode = ThemeMode.DARK
                Toast.makeText(context, R.string.dark_check_box_pushed, Toast.LENGTH_SHORT)
                    .show()
                setAppMode(savedMode)
            }

            if (checkedId == binding.autoCheckBox.id) {
                savedMode = ThemeMode.AUTO
                Toast.makeText(context, R.string.auto_check_box_pushed, Toast.LENGTH_SHORT)
                    .show()
                setAppMode(savedMode)
            }
        }
    }.root

    private fun setAppMode(savedMode: ThemeMode) {
        when (savedMode) {
            ThemeMode.AUTO -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            ThemeMode.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            ThemeMode.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun setCheckedId(savedMode: ThemeMode, binding: StartScreenBinding) {
        when (savedMode) {
            ThemeMode.AUTO -> binding.autoCheckBox.isChecked = true
            ThemeMode.LIGHT -> binding.lightCheckBox.isChecked = true
            ThemeMode.DARK -> binding.darkCheckBox.isChecked = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sharedPrefs?.edit()?.putInt(SELECTED_MODE, savedMode.ordinal)?.apply()
    }

    companion object {
        const val PREF_NAME = "DEFAULT_APP_PREFERENCE_MODE"
        const val SELECTED_MODE = "SELECTED_MODE"
    }
}
