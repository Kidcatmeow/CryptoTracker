package com.example.cryptotracker.ui

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptotracker.R
import com.example.cryptotracker.data.model.Coin
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.caverock.androidsvg.SVG

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import coil.ImageLoader
import coil.request.ImageRequest
import com.bumptech.glide.load.model.StreamEncoder
import com.bumptech.glide.request.target.Target
import java.io.InputStream
// AppGlideModule.kt
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.caverock.androidsvg.SVGImageView

@GlideModule
class AppGlideModule : AppGlideModule()


class CoinRVAdapter(val context: Activity, val list:ArrayList<Coin>) : RecyclerView.Adapter<ViewHolder>(){


//    val option = RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(android.R.drawable.ic_dialog_alert)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rvcoin_viewholder_left,parent,false)

        return ViewHolder(view)


    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Glide.with(context).load(list[position].iconUrl).apply(option).into(holder.ImageView)


//        if (homeImageType == "svg") {
//            SvgLoader.pluck()
//
//        } else {
//            Glide.with(context).load(list[position].iconUrl).into(holder.ImageView);
//        }




//        Glide.with(context).load(list[position].iconUrl).into(holder.ImageView);
        GlideApp.with(context).load(list[position].iconUrl).into(holder.ImageView)
        holder.tvName.text = list[position].name
        holder.tvSymbol.text = list[position].symbol

          }
    }


class ViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView){
    val tvName = itemView.findViewById<TextView>(R.id.textView_coinNameLeft)
    val ImageView = itemView.findViewById<ImageView>(R.id.imageView_coinImageLeft)
    val tvSymbol = itemView.findViewById<TextView>(R.id.textView_coinAbbreviationLeft)

}