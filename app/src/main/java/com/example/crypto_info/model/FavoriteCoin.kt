package com.example.crypto_info.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCoin (
    @PrimaryKey
    val id : String
    )