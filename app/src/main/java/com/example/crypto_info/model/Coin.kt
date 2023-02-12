package com.example.crypto_info.model

import android.media.tv.TvContract
import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import retrofit2.http.Url
import java.io.Serializable
import java.net.URL

data class Coin (
    @SerializedName("id"                 )  @PrimaryKey var id   : String?                  = null,
    @SerializedName("name"               ) var name              : String?                  = null,
    @SerializedName("symbol"             ) var symbol            : String?                  = null,
    @SerializedName("rank"               ) var rank              : Int?                     = null,
    @SerializedName("is_active"          ) var isActive          : Boolean?                 = null,
    @SerializedName("type"               ) var type              : String?                  = null,
    @SerializedName("tags"               ) var tags              : ArrayList<Tags>          = arrayListOf(),
    @SerializedName("team"               ) var team              : ArrayList<Team>          = arrayListOf(),
    @SerializedName("description"        ) var description       : String?                  = null,
    @SerializedName("started_at"         ) var startedAt         : String?                  = null,
    @SerializedName("development_status" ) var developmentStatus : String?                  = null,
    @SerializedName("hardware_wallet"    ) var hardwareWallet    : Boolean?                 = null,
    var isFavourite: Boolean = false

        ):Serializable