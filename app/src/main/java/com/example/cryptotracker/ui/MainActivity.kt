package com.example.cryptotracker.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.cryptotracker.R
import com.example.cryptotracker.data.model.Coin
import com.example.cryptotracker.data.remote.VolleySingleton
import com.example.cryptotracker.ui.adapter.RVAdapter
import kotlin.collections.ArrayList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callVolley()
        val rvCoinList = findViewById<View>(R.id.rvCoinList) as RecyclerView
        rvCoinList.setHasFixedSize(true)
        rvCoinList.layoutManager = LinearLayoutManager(this)

//        val adapterLeft = CoinRVAdapterLeft(this, list)
//        val adapterRight = CoinRVAdapterRight(this, list)
//        val concatAdapter = ConcatAdapter(adapterLeft,adapterRight)
//        rvCoinList.adapter = concatAdapter

        val adapterLeft = RVAdapter(this, list)
        rvCoinList.adapter = adapterLeft

//        var rvCoinList.findViewHolderForAdapterPosition() = n


//        for (i in 0 until list.toString().length){
//
//            if ((i % 5 == 0) || (i != 0)) {
//                val adapterRight = CoinRVAdapterRight(this, list)
//                rvCoinList.adapter = adapterRight
//            } else if ((i % 5 != 0) || (i == 0)) {
//                val adapterLeft = CoinRVAdapterLeft(this, list)
//                rvCoinList.adapter = adapterLeft
//
//            }
//
//        }
//        Log.i("Khing",list.size.toString())

        val adapter = RVAdapter(this,list)
        rvCoinList.adapter = adapter



    }
    val list = ArrayList<Coin>()
    fun callVolley() {
            val url = "https://api.coinranking.com/v2/coins"
            val request = JsonObjectRequest(
                Request.Method.GET, url, null,
                    { response ->
                    val data = response.getJSONObject("data")
                    val data_coin = data.getJSONArray("coins")
                    //coins --> Object[BTC,ETH,BNB,USDT]
                    //val name = BTC.name
                    //val symbol = BTC.symbol
                    //val icon = BTC.iconUrl
                    //val website =BTC.coinrankingUrl
                    //so that means, each index contains a 'coins' object
                    for (i in 0 until data_coin.length()) {
                        //Access the coin object one by one and get their attributes
                        val jsonObj = data_coin.getJSONObject(i)
                        val name = jsonObj.getString("name")
                        val symbol = jsonObj.getString("symbol")
                        val iconUrl = jsonObj.getString("iconUrl")
                        val coinrankingUrl = jsonObj.getString("coinrankingUrl")
                        val rank = jsonObj.getInt("rank")
                        val data = Coin(name, symbol, iconUrl, coinrankingUrl, rank)
                        list.add(data)
                    }

                    Log.i("LOLActivity", list.toString())
                },
                { error ->
                    Log.i("LOLActivity", error.toString())
                    Toast.makeText(applicationContext, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                })
            request.retryPolicy=DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,0,1f)
            VolleySingleton.getInstance(applicationContext).addToRequestQueue(request)
    }



}