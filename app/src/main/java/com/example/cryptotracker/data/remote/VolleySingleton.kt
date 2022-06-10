package com.example.cryptotracker.data.remote

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


//Our application make use of network throughout the lifetime of our app so that's why creating a singleton class of requestqueue is more effective
class VolleySingleton constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: VolleySingleton? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                //Calls the specified function block with 'this' value as its argument and returns this value.
                INSTANCE ?: VolleySingleton(context).also {
                    INSTANCE = it
                }
            }
    }

    //A key concept is that the RequestQueue must be instantiated with the Application context, not an Activity context.
    // This ensures that the RequestQueue will last for the lifetime of your app, instead of being recreated every time the activity is recreated.
    //(for example: when the user rotates the device).
    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue (req : Request<T>){
        requestQueue.add(req)
    }
}
