package ru.alfacampus.homeworkproject.data.db.mappers

import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel
import ru.alfacampus.homeworkproject.data.entities.character.CharacterMarvelEntity

class CharacterMarvelMapper : EntityMapper<CharacterMarvel, CharacterMarvelEntity> {
    override fun mapToEntity(dto: CharacterMarvel): CharacterMarvelEntity {
        return CharacterMarvelEntity(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            modified = dto.modified,
            thumbnail = dto.thumbnail,
            resourceURI = dto.resourceURI,
            urls = dto.urls
        )
    }

    override fun mapFromEntity(entity: CharacterMarvelEntity): CharacterMarvel {
        return CharacterMarvel(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            modified = entity.modified,
            thumbnail = entity.thumbnail,
            resourceURI = entity.resourceURI,
            urls = entity.urls
        )
    }

    fun toEntityList(initial: List<CharacterMarvel>): List<CharacterMarvelEntity> {
        return initial.map { mapToEntity(it) }
    }

    fun fromEntityList(initial: List<CharacterMarvelEntity>): List<CharacterMarvel> {
        return initial.map { mapFromEntity(it) }
    }
}
