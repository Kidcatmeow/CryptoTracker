//package com.example.cryptotracker.ui
//
//import android.app.Activity
//import android.content.ContentValues
//import android.net.Uri
//import android.util.Log
//import android.view.*
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//import com.example.cryptotracker.R
//import com.example.cryptotracker.data.model.Coin
//
//// AppGlideModule.kt
//import com.bumptech.glide.annotation.GlideModule
//import com.bumptech.glide.module.AppGlideModule
//import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp
//import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
//
//
//
//class CoinRVAdapterLeft(val context: Activity, val list:ArrayList<Coin>) : RecyclerView.Adapter<ViewHolderLeft>(){
//    var LAYOUT_ONE = 0;
//    var LAYOUT_TWO = 1;
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderLeft {
//        val view = LayoutInflater.from(context).inflate(R.layout.rvcoin_viewholder_left,parent,false)
//        return ViewHolderLeft(view)
//
//
//
//    }
//
//    override fun getItemCount(): Int = list.size
//
//    override fun onBindViewHolder(holder: ViewHolderLeft, position: Int) {
//
//        for (i in position until list.size) {
//            if ((i % 5 != 0) || (i == 0)) {
//                if (list[position].iconUrl.endsWith(".png", true)) {
//                    GlideApp.with(context).load(list[position].iconUrl).into(holder.ImageView1)
//                } else {
//                    GlideToVectorYou.justLoadImage(
//                        context,
//                        Uri.parse(list[position].iconUrl),
//                        holder.ImageView
//                    )
//                }
//                holder.tvName.text = list[position].name
//                holder.tvSymbol.text = list[position].symbol
//            }
//        }
//    }
//
//}
//
//
//class ViewHolderLeft(view: View) : RecyclerView.ViewHolder(view){
//    val tvName = itemView.findViewById<TextView>(R.id.textView_coinNameLeft)
//    val ImageView1 = itemView.findViewById<ImageView>(R.id.imageView_coinImageLeft)
//    val ImageView = itemView.findViewById<ImageView>(R.id.imageView_coinImageLeft)
//    val tvSymbol = itemView.findViewById<TextView>(R.id.textView_coinAbbreviationLeft)
//
//}
//
