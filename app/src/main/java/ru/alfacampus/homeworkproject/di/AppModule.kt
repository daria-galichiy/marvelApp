package ru.alfacampus.homeworkproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.alfacampus.homeworkproject.data.service.CharactersApi
import ru.alfacampus.homeworkproject.utils.Constants
import ru.alfacampus.homeworkproject.utils.Constants.Companion.BASE_URL
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun baseUrl() = BASE_URL

    @Provides
    fun loggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor())
        .addInterceptor{ chain ->
            val request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter(Constants.API_KEY, Constants.API_PUBLIC_KEY)
                .addQueryParameter(Constants.TIMESTAMP, Constants.timeStamp)
                .addQueryParameter(Constants.HASH, Constants.generateHashValue())
                .build()
            chain.proceed(request.newBuilder().url(url).build())
        }
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): CharactersApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create()
}
