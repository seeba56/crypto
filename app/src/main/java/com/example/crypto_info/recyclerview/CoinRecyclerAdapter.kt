package com.example.crypto_info.recyclerview

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto_info.MainActivity
import com.example.crypto_info.MyApplication
import com.example.crypto_info.R
import com.example.crypto_info.databinding.CoinItemBinding
import com.example.crypto_info.model.Coin

class CoinRecyclerAdapter(val onItemSelectedListener: OnCoinSelectedListener): RecyclerView.Adapter<CoinRecyclerAdapter.CoinViewHolder>() {

    private var  coins = ArrayList<Coin>()


    fun setCoins(coins: ArrayList<Coin>) {
        this.coins.clear()
        this.coins.addAll(coins)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.coin_item, parent, false)

        return CoinViewHolder(view)
    }


    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coins[position]
        holder.bind(coin)
        onItemSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener {
                listener.onCoinSelected(coin)
            }
        }
    }

    companion object{
        const val prefs_file = "MyPrefs"
    }

    fun saveFavourite(isFavourite : String, id: String){
        val sharedPreferences = MyApplication.ApplicationContext.getSharedPreferences(
            prefs_file, Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString(id, isFavourite)
        editor.apply()
    }

    fun retrieveFavourite(id: String): String{
        val sharedPreferences = MyApplication.ApplicationContext.getSharedPreferences(
            prefs_file, Context.MODE_PRIVATE
        )
        return sharedPreferences.getString(id, "false").toString()
    }

    override fun getItemCount(): Int {
        return coins.size
    }
    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(coin: Coin) {

            val binding = CoinItemBinding.bind(itemView)
            binding.nameTv.text = coin.name.toString()
            binding.tagTv.text = "(" + coin.symbol.toString() + ")"
            binding.activeTv.text = coin.isActive.toString()
            if(coin.isActive == true){
                binding.activeTv.text = "active"
                binding.activeTv.setTextColor(Color.GREEN)

            }else{
                binding.activeTv.text = "not active"
                binding.activeTv.setTextColor(Color.RED)
            }

            binding.favoritesButton.isActivated = retrieveFavourite(coin.id.toString()).equals("true")

            binding.favoritesButton.setOnClickListener {

                coin.isFavourite=!coin.isFavourite
                saveFavourite(coin.isFavourite.toString(), coin.id.toString())


                binding.favoritesButton.isActivated=coin.isFavourite
                onItemSelectedListener.onFavoriteSelected(coin)

            }

        }

    }

}






