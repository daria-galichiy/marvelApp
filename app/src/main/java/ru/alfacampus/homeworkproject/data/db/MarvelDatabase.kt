package ru.alfacampus.homeworkproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alfacampus.homeworkproject.data.entities.character.CharacterMarvelEntity

@Database(
    entities = [CharacterMarvelEntity::class],
    version = 1,
    exportSchema = true
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun getCharactersDao(): CharactersDao
}
