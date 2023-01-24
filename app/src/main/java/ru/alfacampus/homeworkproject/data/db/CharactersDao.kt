package ru.alfacampus.homeworkproject.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters")
    fun getCharacters(): LiveData<List<CharacterMarvel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterMarvel: CharacterMarvel)

    @Delete
    suspend fun delete(characterMarvel: CharacterMarvel)

    @Query("DELETE FROM characters")
    fun deleteCharacters()
}
