package ru.alfacampus.homeworkproject.coreNetwork.provider

import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

object ConverterFactoryProvider {
    fun get(): Converter.Factory {
        return GsonConverterFactory.create()
    }
}
