package ru.alfacampus.homeworkproject.coreDb.db.mappers

import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.coreData.data.mappers.EntityMapper
import ru.alfacampus.homeworkproject.coreDb.db.entities.CharacterDbEntity

//TODO: remove redundant interface
interface CharacterDbMapper: EntityMapper<CharacterMarvelEntity, CharacterDbEntity> {
    override fun mapToEntity(dto: CharacterMarvelEntity): CharacterDbEntity
    override fun mapFromEntity(entity: CharacterDbEntity): CharacterMarvelEntity
    fun toEntityList(initial: List<CharacterMarvelEntity>): List<CharacterDbEntity>
    fun fromEntityList(initial: List<CharacterDbEntity>): List<CharacterMarvelEntity>
}
