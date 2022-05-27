package com.example.cryptotracker.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.R

class RVViewholderRight(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvName = itemView.findViewById<TextView>(R.id.textView_coinNameRight)
    val ImageView1 = itemView.findViewById<ImageView>(R.id.imageView_coinImageRight)
    val ImageView = itemView.findViewById<ImageView>(R.id.imageView_coinImageRight)
    val tvUrl = itemView.findViewById<TextView>(R.id.textView_coinUrlRight)

    }

