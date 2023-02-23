package ru.alfacampus.homeworkproject.coreDb.di

import dagger.Binds
import dagger.Module
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CharacterDbMapper
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CharacterDbMapperImpl

@Module
interface MapperDbModule {
    @Binds
    fun bindMapperDb(characterDbMapper: CharacterDbMapperImpl): CharacterDbMapper
}
