package com.example.inseego.DataClass

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object DeviceServiceBuilder{
    private const val URL = "http://10.20.11.22:8091/"
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okhttp = OkHttpClient.Builder().addInterceptor(logger)
    private val builder = Retrofit.Builder().baseUrl(URL)
        .client(okhttp.build())
        .addConverterFactory(GsonConverterFactory.create())
    private val retrofit = builder.build()


    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)


    }
}


