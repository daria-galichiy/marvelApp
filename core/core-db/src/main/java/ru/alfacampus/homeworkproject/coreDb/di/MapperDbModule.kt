package ru.alfacampus.homeworkproject.coreDb.di

import dagger.Binds
import dagger.Module
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CharacterDbMapper
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CharacterDbMapperImpl
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CreatedCharacterDbMapper
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CreatedCharacterDbMapperImpl

@Module
interface MapperDbModule {
    @Binds
    fun bindCharacterMarvelMapperDb(characterDbMapper: CharacterDbMapperImpl): CharacterDbMapper

    @Binds
    fun bindCreatedCharacterMapperDb(createdCharacterDbMapper: CreatedCharacterDbMapperImpl): CreatedCharacterDbMapper
}
