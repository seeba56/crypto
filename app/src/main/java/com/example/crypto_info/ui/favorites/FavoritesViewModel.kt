package com.example.crypto_info.ui.favorites

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_info.database.AppDataBase
import com.example.crypto_info.model.Coin
import com.example.crypto_info.model.FavoriteCoin
import com.example.crypto_info.network.Network
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    val favouriteCoins : MutableLiveData<List<Coin>> = MutableLiveData<List<Coin>>()
    val favCoins : MutableLiveData<List<String>> = MutableLiveData<List<String>>()
    fun getCoins(context : Context) {
        viewModelScope.launch {
            favCoins.value = AppDataBase.getDatabase(context).coinDao().getAll()
        }
    }

    fun insertFavouriteCoin(context: Context, coin: Coin){
        viewModelScope.launch {
            AppDataBase.getDatabase(context).coinDao().insertAll(FavoriteCoin(coin.id!!))
        }
    }

    fun deleteFavouriteCoin(context: Context,coin: Coin){
        viewModelScope.launch {
            AppDataBase.getDatabase(context).coinDao().delete(FavoriteCoin(coin.id!!))

        }
    }

    fun getFavouriteCoins(context: Context){
        viewModelScope.launch {
            val startList=AppDataBase.getDatabase(context).coinDao().getAll()
            val responses = startList.map {
                Network().getservice().getCoinById(it)
            }

            favouriteCoins.value=responses as List<Coin>

        }



    }


}