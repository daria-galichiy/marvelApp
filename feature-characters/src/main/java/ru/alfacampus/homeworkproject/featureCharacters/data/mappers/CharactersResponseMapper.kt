package ru.alfacampus.homeworkproject.featureCharacters.data.mappers

import ru.alfacampus.homeworkproject.featureCharacters.data.dto.character.*
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharactersResponseEntity
import ru.alfacampus.homeworkproject.coreData.data.mappers.EntityMapper
import javax.inject.Inject


class CharactersResponseMapper @Inject constructor() :
    EntityMapper<CharactersResponse, CharactersResponseEntity> {

    override fun mapToEntity(dto: CharactersResponse): CharactersResponseEntity {
        return with(dto) {
            CharactersResponseEntity(
                code = code,
                status = status,
                copyright = copyright,
                attributionText = attributionText,
                attributionHTML = attributionHTML,
                etag = etag,
                data = DataMapper().mapToEntity(data)
            )
        }
    }

    override fun mapFromEntity(entity: CharactersResponseEntity): CharactersResponse {
        return with(entity) {
            CharactersResponse(
                code = code,
                status = status,
                copyright = copyright,
                attributionText = attributionText,
                attributionHTML = attributionHTML,
                etag = etag,
                data = DataMapper().mapFromEntity(data)
            )
        }
    }
}
