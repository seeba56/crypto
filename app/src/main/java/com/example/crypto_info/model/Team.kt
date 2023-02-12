package com.example.crypto_info.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Team (

    @SerializedName("id"       ) var id       : String? = null,
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("position" ) var position : String? = null

): Serializable