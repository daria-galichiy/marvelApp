package ru.alfacampus.homeworkproject.coreDb.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.alfacampus.homeworkproject.coreDb.db.MarvelDatabase
import ru.alfacampus.homeworkproject.coreDb.db.dao.CharactersDao
import javax.inject.Singleton


@Module(
    includes = [MapperDbModule::class]
)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMarvelDatabase(context: Context) =
        Room.databaseBuilder(
//            application.applicationContext,
            context,
            MarvelDatabase::class.java,
            "marvel_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideCharactersDao(appDatabase: MarvelDatabase): CharactersDao =
        appDatabase.getCharactersDao()
}
