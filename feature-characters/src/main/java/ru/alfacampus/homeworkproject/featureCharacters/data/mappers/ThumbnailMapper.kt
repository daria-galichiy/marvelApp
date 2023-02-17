package ru.alfacampus.homeworkproject.featureCharacters.data.mappers

import ru.alfacampus.homeworkproject.featureCharacters.data.dto.character.Thumbnail
import ru.alfacampus.homeworkproject.coreData.data.entities.character.ThumbnailEntity
import ru.alfacampus.homeworkproject.coreData.data.mappers.EntityMapper


class ThumbnailMapper : EntityMapper<Thumbnail, ThumbnailEntity> {
    override fun mapToEntity(dto: Thumbnail): ThumbnailEntity {
        return with(dto) {
            ThumbnailEntity(
                id = id,
                path = path,
                extension = extension
            )
        }
    }

    override fun mapFromEntity(entity: ThumbnailEntity): Thumbnail {
        return with(entity) {
            Thumbnail(
                id = id,
                path = path,
                extension = extension
            )
        }
    }
}
