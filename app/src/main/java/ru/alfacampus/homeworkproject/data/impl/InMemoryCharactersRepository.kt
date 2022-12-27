package ru.alfacampus.homeworkproject.data.impl

import androidx.lifecycle.MutableLiveData
import ru.alfacampus.homeworkproject.data.CharactersRepository
import ru.alfacampus.homeworkproject.dto.CharacterMarvel

class InMemoryCharactersRepository : CharactersRepository {
    private var nextId = GENERATED_POSTS_AMOUNT.toLong()

    private var characters
        get() = checkNotNull(data.value) {
            "Data value shouldn't be null"
        }
        set(value) {
            data.value = value
        }

    override val data = MutableLiveData(
        List(10) { index ->
            CharacterMarvel(
                id = index + 1L,
                name = "IRON MAN " + (index+1),
                description = "Some description " + (index+1),
                thumbnail = "iron_man_sample.jpg"
            )
        }
    )

    override fun delete(characterId: Long) {
        characters = characters.filterNot { it.id == characterId }
    }

    override fun save(characterMarvel: CharacterMarvel) {
        if (characterMarvel.id == CharactersRepository.NEW_CHARACTER_ID) insert(characterMarvel) else update(characterMarvel)
    }

    private fun insert(characterMarvel: CharacterMarvel) {
        nextId += 1
        data.value = listOf(characterMarvel.copy(
            id = nextId,
            name = "IRON MAN $nextId",
            description = "Some description $nextId",
            thumbnail = "iron_man_sample.jpg"
        )) + characters
    }

    private fun update(characterMarvel: CharacterMarvel) {
        data.value = characters.map {
            if (it.id == characterMarvel.id) characterMarvel else it
        }
    }

    private companion object {
        const val GENERATED_POSTS_AMOUNT = 10
    }
}
