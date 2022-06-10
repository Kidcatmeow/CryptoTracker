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
        //create a list of coins to store the data
        val list = ArrayList<Coin>()
        fun callVolley(context:Context) {
            val url = "https://api.coinranking.com/v2/coins"

            // Formulate the request and handle the response.
            val request = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    val data = response.getJSONObject("data")
                    val data_coin = data.getJSONArray("coins")
                    /*coins --> Object[BTC,ETH,BNB,USDT]
                    val name = BTC.name
                    val symbol = BTC.symbol
                    val icon = BTC.iconUrl
                    val website =BTC.coinrankingUrl
                    so that means, each index contains a 'coins' object */

                    for (i in 0 until data_coin.length()) {
                        //Access the coin object one by one and get their attributes
                        val jsonObj = data_coin.getJSONObject(i)
                        val name = jsonObj.getString("name")
                        val symbol = jsonObj.getString("symbol")
                        val iconUrl = jsonObj.getString("iconUrl")
                        val coinrankingUrl = jsonObj.getString("coinrankingUrl")
                        val rank = jsonObj.getInt("rank")
                        //input all attributes into a data variable
                        val data = Coin(name, symbol, iconUrl, coinrankingUrl, rank)
                        //store that data into the list
                        list.add(data)
                    }
                    //check if volley successfully get the list of coin through logcat
                    Log.i("Activity", list.toString())
                },
                { error -> // Handle error
                    Log.i("Activity", error.toString())
                    Toast.makeText(context, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                })


            //Volley request policy , only one time request to avoid duplicate transaction
            request.retryPolicy=DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, // By default, Volley sets all socket and connection timeouts to 5 seconds for all requests.
                0, //0 means no retry
                1f)

            // Add the request to the RequestQueue.
            VolleySingleton.getInstance(context).addToRequestQueue(request)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvCoinList = findViewById<View>(R.id.rvCoinList) as RecyclerView
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