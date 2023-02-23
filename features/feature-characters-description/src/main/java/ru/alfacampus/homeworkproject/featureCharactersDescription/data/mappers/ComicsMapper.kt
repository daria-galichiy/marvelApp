package ru.alfacampus.homeworkproject.featureCharactersDescription.data.mappers

import ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics.ComicsResponse
import ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics.Data
import ru.alfacampus.homeworkproject.coreData.data.entities.comics.ComicsResponseEntity
import ru.alfacampus.homeworkproject.coreData.data.entities.comics.DataEntity
import ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics.Result
import ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics.Thumbnail
import ru.alfacampus.homeworkproject.coreData.data.entities.comics.ResultEntity
import ru.alfacampus.homeworkproject.coreData.data.entities.comics.ThumbnailEntity
import javax.inject.Inject


class ComicsMapper @Inject constructor() {

    fun comicsResponseMapToEntity(dto: ComicsResponse): ComicsResponseEntity {
        return with(dto) {
            ComicsResponseEntity(
                code = code,
                status = status,
                copyright = copyright,
                attributionText = attributionText,
                attributionHTML = attributionHTML,
                etag = etag,
                data = dataMapToEntity(data)
            )
        }
    }

    fun dataMapToEntity(dto: Data): DataEntity {
        return with(dto) {
            DataEntity(
                offset = offset,
                limit = limit,
                total = total,
                count = count,
                results = listResultMapToEntities(results)
            )
        }
    }

    fun listResultMapToEntities(dto: List<Result>): List<ResultEntity> =
        dto.map { resultMapToEntity(it) }

    fun resultMapToEntity(dto: Result): ResultEntity {
        return with(dto) {
            ResultEntity(
                id = id,
                digitalId = digitalId,
                title = title,
                issueNumber = issueNumber,
                variantDescription = variantDescription,
                description = description,
                modified = modified,
                isbn = isbn,
                upc = upc,
                diamondCode = diamondCode,
                ean = ean,
                issn = issn,
                format = format,
                pageCount = pageCount,
                thumbnail = thumbnailMapToEntity(thumbnail),
            )
        }
    }

    fun thumbnailMapToEntity(dto: Thumbnail): ThumbnailEntity {
        return with(dto) {
            ThumbnailEntity(
                path = path,
                extension = extension
            )
        }
    }
}
