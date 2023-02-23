package ru.alfacampus.homeworkproject.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import ru.alfacampus.homeworkproject.coreDi.dependencies.FeatureExternalDepsContainer
import ru.alfacampus.homeworkproject.coreDi.dependencies.FeatureExternalDepsProvider
import ru.alfacampus.homeworkproject.R as mainR
import ru.alfacampus.homeworkproject.featureSplashScreen.R as splashR
import ru.alfacampus.homeworkproject.databinding.ActivityMainBinding
import ru.alfacampus.homeworkproject.di.MarvelApp
import ru.alfacampus.homeworkproject.navigation.NavigationFlow
import ru.alfacampus.homeworkproject.navigation.Navigator
import ru.alfacampus.homeworkproject.navigation.ToFlowNavigatable
import javax.inject.Inject


class MainActivity : AppCompatActivity(),
    FeatureExternalDepsProvider,
    ToFlowNavigatable {

    @Inject
    override lateinit var deps: FeatureExternalDepsContainer
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MarvelApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(mainR.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                mainR.id.startScreenFragment -> binding.bottomNavBar.visibility = View.GONE
                splashR.id.customViewScreenFragment -> binding.bottomNavBar.visibility = View.GONE
                else -> binding.bottomNavBar.visibility = View.VISIBLE
            }
        }
        NavigationUI.setupWithNavController(binding.bottomNavBar, navController)

        navigator.navController = navController
    }

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator.navigateToFlow(flow)
    }
}
