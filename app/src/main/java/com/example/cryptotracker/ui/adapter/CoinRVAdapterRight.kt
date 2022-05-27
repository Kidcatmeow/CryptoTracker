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
//class CoinRVAdapterRight(val context: Activity, val list:ArrayList<Coin>) : RecyclerView.Adapter<ViewHolderRight>(){
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRight {
//
//
//        val view = LayoutInflater.from(context).inflate(R.layout.rvcoin_viewholder_right,parent,false)
//        return ViewHolderRight(view)
//    }
//
//    override fun getItemCount(): Int = list.size
//
//    override fun onBindViewHolder(holder: ViewHolderRight, position: Int) {
//
//        for (i in position until list.size) {
//            if ((i % 5 == 0) || (i != 0)){
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
//                holder.tvUrl.text = list[position].coinrankingUrl
//
//            }
//
//        }
//
//
//
//
//
//
//    }
//
//}
//
//
//class ViewHolderRight(view: View) : RecyclerView.ViewHolder(view){
//
//        val tvName = itemView.findViewById<TextView>(R.id.textView_coinNameRight)
//        val ImageView1 = itemView.findViewById<ImageView>(R.id.imageView_coinImageRight)
//        val ImageView = itemView.findViewById<ImageView>(R.id.imageView_coinImageRight)
//        val tvUrl = itemView.findViewById<TextView>(R.id.textView_coinUrlRight)
//
//
//
//}
//
