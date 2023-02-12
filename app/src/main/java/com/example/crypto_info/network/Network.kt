package com.example.crypto_info.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    private val service:NetworkService
    private val baseUrl="https://api.coinpaprika.com"
    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient= OkHttpClient.Builder().addInterceptor(logging)
        val retrofit= Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build()).build()
        service=retrofit.create(NetworkService::class.java)
    }
    fun getservice():NetworkService = service
}