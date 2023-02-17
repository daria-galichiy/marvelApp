package ru.alfacampus.homeworkproject.featureCharacters.data.mappers

import ru.alfacampus.homeworkproject.featureCharacters.data.dto.character.CharacterMarvel
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.coreData.data.mappers.EntityMapper


class CharactersMapper : EntityMapper<CharacterMarvel, CharacterMarvelEntity> {
    override fun mapToEntity(dto: CharacterMarvel): CharacterMarvelEntity {
        return with(dto) {
            CharacterMarvelEntity(
                id = id,
                name = name,
                description = description,
                modified = modified,
                thumbnail = ThumbnailMapper().mapToEntity(thumbnail),
                resourceURI = resourceURI,
                urls = UrlMapper().listUrlMapToEntities(urls)
            )
        }
    }

    override fun mapFromEntity(entity: CharacterMarvelEntity): CharacterMarvel {
        return with(entity) {
            CharacterMarvel(
                id = id,
                name = name,
                description = description,
                modified = modified,
                thumbnail = ThumbnailMapper().mapFromEntity(thumbnail),
                resourceURI = resourceURI,
                urls = UrlMapper().listUrlMapFromEntities(urls)
            )
        }
    }

    fun toEntityList(initial: List<CharacterMarvel>): List<CharacterMarvelEntity> {
        return initial.map { mapToEntity(it) }
    }

    fun fromEntityList(initial: List<CharacterMarvelEntity>): List<CharacterMarvel> {
        return initial.map { mapFromEntity(it) }
    }
}
