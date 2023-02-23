package ru.alfacampus.homeworkproject.coreNetwork.provider

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.alfacampus.homeworkproject.coreNetwork.utils.Constants


object OkHttpClientProvider {
    fun get(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request.url.newBuilder()
                    .addQueryParameter(Constants.API_KEY, Constants.API_PUBLIC_KEY)
                    .addQueryParameter(Constants.TIMESTAMP, Constants.timeStamp)
                    .addQueryParameter(Constants.HASH, Constants.generateHashValue())
                    .build()
                chain.proceed(request.newBuilder().url(url).build())
            }
            .build()
    }
}
