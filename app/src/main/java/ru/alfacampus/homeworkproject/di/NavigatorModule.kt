package ru.alfacampus.homeworkproject.di

import dagger.Module
import ru.alfacampus.homeworkproject.navigation.Navigator

@Module
object NavigatorModule {
    fun provideNavigator(): Navigator = Navigator()
}
