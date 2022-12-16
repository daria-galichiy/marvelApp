package ru.alfacampus.homeworkproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.alfacampus.homeworkproject.R
import ru.alfacampus.homeworkproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, StartScreenFragment())
            .commit()
    }
}
