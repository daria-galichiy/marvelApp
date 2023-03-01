package ru.alfacampus.homeworkproject.coreDb.db.mappers

import ru.alfacampus.homeworkproject.coreData.data.entities.character.CreatedCharacterEntity
import ru.alfacampus.homeworkproject.coreDb.db.entities.CreatedCharacterDbEntity
import javax.inject.Inject


class CreatedCharacterDbMapperImpl @Inject constructor() : CreatedCharacterDbMapper {

    override fun mapToEntity(dto: CreatedCharacterEntity): CreatedCharacterDbEntity {
        with(dto) {
            return if (id != null)
                CreatedCharacterDbEntity(
                    id = id!!,
                    name = name,
                    description = description,
                    thumbnailUri = thumbnailUri
                )
            else CreatedCharacterDbEntity(
                name = name,
                description = description,
                thumbnailUri = thumbnailUri
            )
        }
    }

    override fun mapFromEntity(entity: CreatedCharacterDbEntity): CreatedCharacterEntity {
        return with(entity) {
            CreatedCharacterEntity(
                id = id,
                name = name,
                description = description,
                thumbnailUri = thumbnailUri
            )
        }
    }

    override fun toEntityList(initial: List<CreatedCharacterEntity>): List<CreatedCharacterDbEntity> {
        return initial.map { mapToEntity(it) }
    }

    override fun fromEntityList(initial: List<CreatedCharacterDbEntity>): List<CreatedCharacterEntity> {
        return initial.map { mapFromEntity(it) }
    }
}
