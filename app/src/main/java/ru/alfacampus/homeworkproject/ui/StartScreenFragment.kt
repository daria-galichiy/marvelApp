package ru.alfacampus.homeworkproject.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import ru.alfacampus.homeworkproject.R
import ru.alfacampus.homeworkproject.databinding.StartScreenBinding
import ru.alfacampus.homeworkproject.helpers.ThemeMode


class StartScreenFragment : Fragment() {

    private val sharedPrefs by lazy {
        this.activity?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private var savedMode = ThemeMode.AUTO
    private var changeThemeMessage: Int = 0

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

        setCheckedId(savedMode, binding).isChecked = true
        setDefaultNightMode(setAppMode(savedMode))

        binding.themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == binding.lightCheckBox.id) {
                savedMode = ThemeMode.LIGHT
                changeThemeMessage = R.string.light_check_box_pushed
            }

            if (checkedId == binding.darkCheckBox.id) {
                savedMode = ThemeMode.DARK
                changeThemeMessage = R.string.dark_check_box_pushed
            }

            if (checkedId == binding.autoCheckBox.id) {
                savedMode = ThemeMode.AUTO
                changeThemeMessage = R.string.auto_check_box_pushed
            }
            Toast.makeText(context, changeThemeMessage, Toast.LENGTH_SHORT)
                .show()
            setDefaultNightMode(setAppMode(savedMode))
        }
    }.root

    private fun setAppMode(savedMode: ThemeMode): Int {
        return when (savedMode) {
            ThemeMode.AUTO -> MODE_NIGHT_FOLLOW_SYSTEM
            ThemeMode.LIGHT -> MODE_NIGHT_NO
            ThemeMode.DARK -> MODE_NIGHT_YES
        }
    }

    private fun setCheckedId(savedMode: ThemeMode, binding: StartScreenBinding): AppCompatRadioButton {
        return when (savedMode) {
            ThemeMode.AUTO -> binding.autoCheckBox
            ThemeMode.LIGHT -> binding.lightCheckBox
            ThemeMode.DARK -> binding.darkCheckBox
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
