package com.example.cryptotracker.ui

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.cryptotracker.R
import com.example.cryptotracker.data.model.Coin
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callVolley()
        val rvCoinList = findViewById<View>(R.id.rvCoinList) as RecyclerView
        //create adapter
        rvCoinList.setHasFixedSize(true)
        rvCoinList.layoutManager = LinearLayoutManager(this)
        val adapter = CoinRVAdapter(this,list)
        rvCoinList.adapter = adapter



    }
    private val list = ArrayList<Coin>()
    private fun callVolley() {
        val url = "https://api.coinranking.com/v2/coins"
        val request = JsonObjectRequest(
            Request.Method.GET,url,null,
            {response->


                val data = response.getJSONObject("data")
                val data_coin = data.getJSONArray("coins")
                //coins --> Object[BTC,ETH,BNB,USDT]

                //val name = BTC.name
                //val symbol = BTC.symbol
                //val icon = BTC.iconUrl
                //val website =BTC.coinrankingUrl

                //so that means, each index contains a 'coins' object
                for(i in 0 until data_coin.length() ){
                    //Access the coin object one by one and get their attributes
                    val jsonObj = data_coin.getJSONObject(i)
                    val name = jsonObj.getString("name")
                    val symbol = jsonObj.getString("symbol")
                    val iconUrl = jsonObj.getString("iconUrl")
                    val coinrankingUrl = jsonObj.getString("coinrankingUrl")
                    val rank = jsonObj.getInt("rank")

                    val data = Coin(name,symbol,iconUrl,coinrankingUrl,rank)
                    list.add(data)

                }

                Log.i("LOLActivity", list.toString())
            },
            { error->
                Log.i("LOLActivity",error.toString())
                Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_SHORT).show() })
        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }
}