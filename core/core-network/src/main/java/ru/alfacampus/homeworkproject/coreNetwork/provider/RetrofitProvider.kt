package ru.alfacampus.homeworkproject.coreNetwork.provider

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit


object RetrofitProvider {
    fun get(
        baseUrl: String,
        convrterFactory: Converter.Factory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(convrterFactory)
            .client(okHttpClient)
            .build()
    }
}
