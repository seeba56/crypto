package com.example.crypto_info.ui.favorites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crypto_info.databinding.FragmentFavoritesBinding
import com.example.crypto_info.model.Coin
import com.example.crypto_info.recyclerview.CoinRecyclerAdapter
import com.example.crypto_info.recyclerview.OnCoinSelectedListener
import com.example.crypto_info.ui.CoinDetailActivity

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val favoritesViewModel: FavoritesViewModel by activityViewModels()
    private lateinit var  adapterCoin:CoinRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        val root: View = binding.root

        favoritesViewModel.favouriteCoins.observe(viewLifecycleOwner){
            binding.favoritesRv.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            adapterCoin = CoinRecyclerAdapter(adapterListener)
            adapterCoin.setCoins(it.toCollection(arrayListOf()))

            binding.favoritesRv.adapter = adapterCoin

        }
        favoritesViewModel.getFavouriteCoins(requireContext())
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val adapterListener=object : OnCoinSelectedListener {
        override fun onCoinSelected(coin: Coin) {
            val intent= Intent(activity, CoinDetailActivity::class.java).apply {
                putExtra("coin",coin)
            }
            startActivity(intent)
        }

        override fun onFavoriteSelected( coin: Coin) {

            if (coin.isFavourite){
                favoritesViewModel.insertFavouriteCoin(requireContext(),coin)

            }else{
                favoritesViewModel.deleteFavouriteCoin(requireContext(),coin)
            }

        }
    }
}