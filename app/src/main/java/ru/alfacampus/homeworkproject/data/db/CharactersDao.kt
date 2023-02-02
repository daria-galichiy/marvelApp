package ru.alfacampus.homeworkproject.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.alfacampus.homeworkproject.data.entities.character.CharacterMarvelEntity

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters")
    fun getCharacters(): LiveData<List<CharacterMarvelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterMarvel: CharacterMarvelEntity)

    @Delete
    suspend fun delete(characterMarvel: CharacterMarvelEntity)

    @Query("DELETE FROM characters")
    fun deleteCharacters()
}
