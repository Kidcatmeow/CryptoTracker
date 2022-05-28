package com.example.cryptotracker.ui


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.cryptotracker.R
import com.example.cryptotracker.data.model.Coin
import com.example.cryptotracker.data.remote.VolleySingleton
import com.example.cryptotracker.ui.adapter.rvCoinAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.collections.ArrayList



class MainActivity : AppCompatActivity() {

    companion object {

        val list = ArrayList<Coin>()
        fun callVolley(context:Context) {
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
                    Toast.makeText(context, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                })
            request.retryPolicy=DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,0,1f)
            VolleySingleton.getInstance(context).addToRequestQueue(request)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvCoinList = findViewById<View>(R.id.rvCoinList) as RecyclerView
        rvCoinList.setHasFixedSize(true)
        rvCoinList.layoutManager = LinearLayoutManager(this)
        val adapter = rvCoinAdapter(this,list){
            coin->
             //this 'coin' is simply list[it] from onCoinClicked
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(coin.coinrankingUrl))
                startActivity(intent)

        }
        rvCoinList.adapter = adapter




    }
    


}