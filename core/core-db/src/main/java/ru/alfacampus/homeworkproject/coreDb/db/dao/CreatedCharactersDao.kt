package ru.alfacampus.homeworkproject.coreDb.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.alfacampus.homeworkproject.coreDb.db.entities.CreatedCharacterDbEntity

@Dao
interface CreatedCharactersDao {

    @Query("SELECT * FROM created_characters")
    fun getCreatedCharacters(): Flow<List<CreatedCharacterDbEntity>>

    @Query("SELECT * FROM created_characters WHERE id=:createdCharacterId")
    suspend fun getCreatedCharacterById(createdCharacterId: Int): CreatedCharacterDbEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCreatedCharacter(createdCharacter: CreatedCharacterDbEntity)

    @Delete
    suspend fun deleteCreatedCharacter(createdCharacter: CreatedCharacterDbEntity)

    @Query("DELETE FROM created_characters")
    fun deleteCreatedCharacters()
}
