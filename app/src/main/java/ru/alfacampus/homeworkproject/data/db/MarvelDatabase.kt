package ru.alfacampus.homeworkproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel

@Database(
    entities = [CharacterMarvel::class],
    version = 2,
    exportSchema = true
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun getCharactersDao(): CharactersDao
}
