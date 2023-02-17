package ru.alfacampus.homeworkproject.featureCharacters.data.mappers

import ru.alfacampus.homeworkproject.featureCharacters.data.dto.character.Data
import ru.alfacampus.homeworkproject.coreData.data.entities.character.DataEntity
import ru.alfacampus.homeworkproject.coreData.data.mappers.EntityMapper


class DataMapper : EntityMapper<Data, DataEntity> {
    override fun mapToEntity(dto: Data): DataEntity {
        return with(dto) {
            DataEntity(
                offset = offset,
                limit = limit,
                total = total,
                count = count,
                results = CharactersMapper().toEntityList(results)
            )
        }
    }

    override fun mapFromEntity(entity: DataEntity): Data {
        return with(entity) {
            Data(
                offset = offset,
                limit = limit,
                total = total,
                count = count,
                results = CharactersMapper().fromEntityList(results)
            )
        }
    }
}
