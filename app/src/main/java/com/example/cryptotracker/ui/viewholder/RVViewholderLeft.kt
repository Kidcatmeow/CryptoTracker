package com.example.cryptotracker.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.R

class ViewHolderLeft(view: View) : RecyclerView.ViewHolder(view){
    val tvName = itemView.findViewById<TextView>(R.id.textView_coinNameLeft)
    val ImageView1 = itemView.findViewById<ImageView>(R.id.imageView_coinImageLeft)
    val ImageView = itemView.findViewById<ImageView>(R.id.imageView_coinImageLeft)
    val tvSymbol = itemView.findViewById<TextView>(R.id.textView_coinAbbreviationLeft)

}