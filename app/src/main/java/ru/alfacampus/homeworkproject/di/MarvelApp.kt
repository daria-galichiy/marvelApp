package ru.alfacampus.homeworkproject.di

import android.app.Application
import ru.alfacampus.homeworkproject.navigation.Navigator


class MarvelApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(this, Navigator())
    }
}
