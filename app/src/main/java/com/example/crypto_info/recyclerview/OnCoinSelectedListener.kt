package com.example.crypto_info.recyclerview

import com.example.crypto_info.model.Coin

interface OnCoinSelectedListener {
    fun onCoinSelected(coin: Coin)
    fun onFavoriteSelected(coin: Coin)
}