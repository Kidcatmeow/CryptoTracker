package com.example.cryptotracker.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.cryptotracker.R
import com.example.cryptotracker.data.model.Coin
import com.example.cryptotracker.data.remote.VolleySingleton
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //call the function callVolley from main activity to load the data .
        MainActivity.callVolley(applicationContext)
        setContentView(R.layout.activity_splash_screen)

        //Set up the app logo animation on the splashscreen
        //Delay of 2 second with an animation fade in and out
        imageView_appLogo.alpha = 0f
        imageView_appLogo.animate().setDuration(2000).alpha(1f).withEndAction {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }


    }
}