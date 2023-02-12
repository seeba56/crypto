package com.example.crypto_info.ui.home


import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_info.database.AppDataBase
import com.example.crypto_info.model.Coin
import com.example.crypto_info.model.FavoriteCoin
import com.example.crypto_info.network.Network
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    val coinList = MutableLiveData<ArrayList<Coin>>()
    fun getCoinList(){
        viewModelScope.launch {
            coinList.value=Network().getservice().getCoins()
            //Log.d(TAG, "getCoinList: ${coinList.value}")
        }
    }
    fun insertFavouriteCoin(context: Context, coin: Coin){
        viewModelScope.launch {
            AppDataBase.getDatabase(context).coinDao().insertAll(FavoriteCoin(coin.id!!))
            coin.isFavourite = true

        }

    }
    fun deleteFavouriteCoin(context: Context,coin: Coin){
        viewModelScope.launch {
            AppDataBase.getDatabase(context).coinDao().delete(FavoriteCoin(coin.id!!))
            coin.isFavourite = false

        }

    }
}