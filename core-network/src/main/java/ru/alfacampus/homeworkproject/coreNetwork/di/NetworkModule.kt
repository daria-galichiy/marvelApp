package ru.alfacampus.homeworkproject.coreNetwork.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import ru.alfacampus.homeworkproject.coreNetwork.provider.ConverterFactoryProvider
import ru.alfacampus.homeworkproject.coreNetwork.provider.HttpLoggingInterceptorProvider
import ru.alfacampus.homeworkproject.coreNetwork.provider.OkHttpClientProvider
import ru.alfacampus.homeworkproject.coreNetwork.provider.RetrofitProvider
import ru.alfacampus.homeworkproject.coreNetwork.utils.Constants.Companion.BASE_URL


@Module
object NetworkModule {
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit = RetrofitProvider.get(baseUrl, converterFactory, okHttpClient)

    @Provides
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    fun provideConverterFactory(): Converter.Factory = ConverterFactoryProvider.get()

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClientProvider.get(loggingInterceptor)

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptorProvider.get()
}
