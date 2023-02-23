package ru.alfacampus.homeworkproject.featureCharacters.data.mappers

import ru.alfacampus.homeworkproject.featureCharacters.data.dto.character.Url
import ru.alfacampus.homeworkproject.coreData.data.entities.character.UrlEntity
import ru.alfacampus.homeworkproject.coreData.data.mappers.EntityMapper

class UrlMapper : EntityMapper<Url, UrlEntity> {
    override fun mapToEntity(dto: Url): UrlEntity {
        return with(dto) {
            UrlEntity(
                type = type,
                url = url
            )
        }
    }

    override fun mapFromEntity(entity: UrlEntity): Url {
        return with(entity) {
            Url(
                type = type,
                url = url
            )
        }
    }

    fun listUrlMapToEntities(dto: List<Url>): List<UrlEntity> =
        dto.map { mapToEntity(it) }

    fun listUrlMapFromEntities(entity: List<UrlEntity>): List<Url> =
        entity.map { mapFromEntity(it) }
}
