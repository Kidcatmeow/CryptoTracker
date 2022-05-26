package com.example.cryptotracker.ui

import android.app.Activity
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.cryptotracker.R
import com.example.cryptotracker.data.model.Coin

// AppGlideModule.kt
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


@GlideModule
class AppGlideModule : AppGlideModule()

class CoinRVAdapter(val context: Activity, val list:ArrayList<Coin>) : RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rvcoin_viewholder_left,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = list[position].name
        holder.tvSymbol.text = list[position].symbol
        GlideToVectorYou.justLoadImage(context, Uri.parse(list[position].iconUrl),holder.ImageView)
          }
    }


class ViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView){
    val tvName = itemView.findViewById<TextView>(R.id.textView_coinNameLeft)
    val ImageView = itemView.findViewById<ImageView>(R.id.imageView_coinImageLeft)
    val tvSymbol = itemView.findViewById<TextView>(R.id.textView_coinAbbreviationLeft)

}