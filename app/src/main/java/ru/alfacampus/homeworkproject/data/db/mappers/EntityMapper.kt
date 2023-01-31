package ru.alfacampus.homeworkproject.data.db.mappers

interface EntityMapper<Dto, Entity> {
    fun mapToEntity(dto: Dto): Entity
    fun mapFromEntity(entity: Entity): Dto
}
