package ru.alfacampus.homeworkproject.coreDb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alfacampus.homeworkproject.coreDb.db.dao.CharactersDao
import ru.alfacampus.homeworkproject.coreDb.db.entities.CharacterDbEntity


@Database(
    entities = [CharacterDbEntity::class],
    version = 1,
    exportSchema = true
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun getCharactersDao(): CharactersDao
}
