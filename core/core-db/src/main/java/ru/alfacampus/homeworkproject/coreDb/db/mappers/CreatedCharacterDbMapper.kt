package ru.alfacampus.homeworkproject.coreDb.db.mappers

import ru.alfacampus.homeworkproject.coreData.data.entities.character.CreatedCharacterEntity
import ru.alfacampus.homeworkproject.coreData.data.mappers.EntityMapper
import ru.alfacampus.homeworkproject.coreDb.db.entities.CreatedCharacterDbEntity

//TODO: remove redundant interface
interface CreatedCharacterDbMapper :
    EntityMapper<CreatedCharacterEntity, CreatedCharacterDbEntity> {
    override fun mapToEntity(dto: CreatedCharacterEntity): CreatedCharacterDbEntity
    override fun mapFromEntity(entity: CreatedCharacterDbEntity): CreatedCharacterEntity
    fun toEntityList(initial: List<CreatedCharacterEntity>): List<CreatedCharacterDbEntity>
    fun fromEntityList(initial: List<CreatedCharacterDbEntity>): List<CreatedCharacterEntity>
}
