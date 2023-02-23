package ru.alfacampus.homeworkproject.coreDb.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.alfacampus.homeworkproject.coreDb.db.entities.CharacterDbEntity

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters")
    fun getCharacters(): Flow<List<CharacterDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterMarvel: CharacterDbEntity)

    @Delete
    suspend fun delete(characterMarvel: CharacterDbEntity)

    @Query("DELETE FROM characters")
    fun deleteCharacters()
}
