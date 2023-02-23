package ru.alfacampus.homeworkproject.coreDb.db.mappers

import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.coreDb.db.entities.CharacterDbEntity
import javax.inject.Inject


class CharacterDbMapperImpl @Inject constructor() : CharacterDbMapper {

    override fun mapToEntity(dto: CharacterMarvelEntity): CharacterDbEntity {
        return with(dto) {
            CharacterDbEntity(
                id = id,
                name = name,
                description = description,
                modified = modified,
                thumbnail = thumbnail,
                resourceURI = resourceURI,
                urls = urls
            )
        }
    }

    override fun mapFromEntity(entity: CharacterDbEntity): CharacterMarvelEntity {
        return with(entity) {
            CharacterMarvelEntity(
                id = id,
                name = name,
                description = description,
                modified = modified,
                thumbnail = thumbnail,
                resourceURI = resourceURI,
                urls = urls
            )
        }
    }

    override fun toEntityList(initial: List<CharacterMarvelEntity>): List<CharacterDbEntity> {
        return initial.map { mapToEntity(it) }
    }

    override fun fromEntityList(initial: List<CharacterDbEntity>): List<CharacterMarvelEntity> {
        return initial.map { mapFromEntity(it) }
    }
}
