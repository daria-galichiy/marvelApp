package ru.alfacampus.homeworkproject.coreNetwork.provider

import okhttp3.logging.HttpLoggingInterceptor

object HttpLoggingInterceptorProvider {
    fun get(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}
