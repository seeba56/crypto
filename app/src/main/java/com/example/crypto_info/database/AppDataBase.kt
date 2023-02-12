package com.example.crypto_info.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crypto_info.model.Coin
import com.example.crypto_info.model.FavoriteCoin

@Database(entities = [FavoriteCoin::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun coinDao(): CoinDao

    companion object{
        private var instance:AppDataBase?=null
        fun getDatabase(context: Context): AppDataBase {
            if(instance==null){
                instance=buildDatabase(context)
            }
            return instance!!
        }

        private fun buildDatabase(context: Context)= Room.databaseBuilder(
            context,
            AppDataBase::class.java, "CoinDatabase"
        ).fallbackToDestructiveMigration().build()

    }

}