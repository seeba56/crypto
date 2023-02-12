package com.example.crypto_info.database

import androidx.room.*
import com.example.crypto_info.model.Coin
import com.example.crypto_info.model.FavoriteCoin

@Dao
interface CoinDao {

    @Query("SELECT id FROM FavoriteCoin")
    suspend fun getAll(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(coin: FavoriteCoin)

    @Delete
    suspend fun delete(coin: FavoriteCoin)

}