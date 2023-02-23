package ru.alfacampus.homeworkproject.coreData.data.mappers

interface EntityMapper<Dto, Entity> {
    fun mapToEntity(dto: Dto): Entity
    fun mapFromEntity(entity: Entity): Dto
}
