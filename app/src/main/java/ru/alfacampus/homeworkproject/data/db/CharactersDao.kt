package ru.alfacampus.homeworkproject.data.db
import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel

interface CharactersDao {
    // TODO implement queries
    @Query("SELECT * FROM characters")
    suspend fun getCharacters(): LiveData<List<CharacterMarvel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterMarvel: CharacterMarvel)

    @Delete
    suspend fun delete(characterMarvel: CharacterMarvel)
}
