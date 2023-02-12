package com.example.crypto_info.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Tags (
    @SerializedName("id"           ) var id          : String? = null,
    @SerializedName("name"         ) var name        : String? = null,
    @SerializedName("coin_counter" ) var coinCounter : Int?    = null,
    @SerializedName("ico_counter"  ) var icoCounter  : Int?    = null
): Serializable