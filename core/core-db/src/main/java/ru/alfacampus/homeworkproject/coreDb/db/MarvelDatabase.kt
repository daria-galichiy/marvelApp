package ru.alfacampus.homeworkproject.coreDb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alfacampus.homeworkproject.coreDb.db.dao.CharactersDao
import ru.alfacampus.homeworkproject.coreDb.db.dao.CreatedCharactersDao
import ru.alfacampus.homeworkproject.coreDb.db.entities.CharacterDbEntity
import ru.alfacampus.homeworkproject.coreDb.db.entities.CreatedCharacterDbEntity


@Database(
    entities = [
        CharacterDbEntity::class,
        CreatedCharacterDbEntity::class],
    version = 1,
    exportSchema = true
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun getCharactersDao(): CharactersDao
    abstract fun getCreatedCharactersDao(): CreatedCharactersDao
}
