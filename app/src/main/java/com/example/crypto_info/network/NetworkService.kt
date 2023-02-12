package com.example.crypto_info.network

import com.example.crypto_info.model.Coin
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {

    @GET("/v1/coins")
    suspend fun getCoins(): ArrayList<Coin>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String) : Coin

}