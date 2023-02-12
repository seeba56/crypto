package com.example.crypto_info.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crypto_info.databinding.FragmentHomeBinding
import com.example.crypto_info.model.Coin
import com.example.crypto_info.recyclerview.CoinRecyclerAdapter
import com.example.crypto_info.recyclerview.OnCoinSelectedListener
import com.example.crypto_info.ui.CoinDetailActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var  adapterCoin:CoinRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        homeViewModel.coinList.observe(viewLifecycleOwner){
            binding.rvCoins.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            adapterCoin = CoinRecyclerAdapter(adapterListener)
            adapterCoin.setCoins(it)

            binding.rvCoins.adapter = adapterCoin

        }
        homeViewModel.getCoinList()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private val adapterListener=object : OnCoinSelectedListener{
        override fun onCoinSelected(coin:Coin) {
            val intent= Intent(activity, CoinDetailActivity::class.java).apply {
                putExtra("coin",coin)
            }
            startActivity(intent)
        }

        override fun onFavoriteSelected( coin:Coin) {

            if (coin.isFavourite){
                homeViewModel.insertFavouriteCoin(requireContext(),coin)

            }else{
                homeViewModel.deleteFavouriteCoin(requireContext(),coin)
            }

        }
    }
}