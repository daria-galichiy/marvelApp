package ru.alfacampus.homeworkproject.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class MarvelDatabase : RoomDatabase() {

    abstract fun getCharactersDao(): CharactersDao

    companion object {
        @Volatile
        private var instance: MarvelDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context): MarvelDatabase {
            return Room.databaseBuilder(
                context,
                MarvelDatabase::class.java,
                "marvel_database"
            ).build()
        }
    }
}
