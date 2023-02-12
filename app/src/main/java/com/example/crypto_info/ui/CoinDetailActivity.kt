package com.example.crypto_info.ui

import android.content.ContentValues.TAG
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.crypto_info.R
import com.example.crypto_info.databinding.ActivityCoinDetailBinding
import com.example.crypto_info.model.Coin
import com.example.crypto_info.model.Team
import com.squareup.picasso.Picasso
import java.lang.System.load

class CoinDetailActivity : AppCompatActivity() {
    lateinit var coin: Coin
    private lateinit var binding : ActivityCoinDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        coin=getIntent().getSerializableExtra("coin") as Coin
        //binding.detailRankTv.text=coin.rank.toString()
        binding.nameRankTv.text = coin.name
        binding.symbolTv.text = coin.symbol
        binding.descriptionTv.text = coin.description.toString()
        //Log.d(TAG, "onCreate: ${coin.description}")




    }
}