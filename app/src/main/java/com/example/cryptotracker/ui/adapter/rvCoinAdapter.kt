package com.example.cryptotracker.ui.adapter
import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.R
import com.example.cryptotracker.data.model.Coin

import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import androidx.core.content.ContextCompat


class rvCoinAdapter(val context: Activity, val list:ArrayList<Coin>,var onCoinClicked : (Coin)->Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //STEP: 2
    //we check which ItemViewType was passed by function 'getItemViewType'
    //if left --> create viewHolderLeft
    //if right --> create viewHolderRight
    //After this, we move to onBindViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)



        return when (viewType) {
            R.layout.rvcoin_viewholder_right -> ViewHolderRight(inflater.inflate(viewType, parent, false),{positionOfCoin->
                onCoinClicked(list[positionOfCoin])
            })


            R.layout.rvcoin_viewholder_left -> ViewHolderLeft(inflater.inflate(viewType, parent, false))

            else -> throw IllegalArgumentException("Unsupported layout") // in case populated with a model we don't know how to display.
        }
    }



    //STEP: 3
    //we check which viewHolder was created
    //if right we bind something
    //if left we bind the other thing
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (holder) {
            is ViewHolderRight -> {
                if (list[position].iconUrl.endsWith(".png", true)) {
                    GlideApp.with(context).load(list[position].iconUrl).into(holder.ImageView1)
                } else {
                    GlideToVectorYou.justLoadImage(
                        context,
                        Uri.parse(list[position].iconUrl),
                        holder.ImageView
                    )
                }
                holder.tvName.text = list[position].name
                holder.tvUrl.text = list[position].coinrankingUrl

            }

            is ViewHolderLeft -> {
                if (list[position].iconUrl.endsWith(".png", true)) {
                    GlideApp.with(context).load(list[position].iconUrl).into(holder.ImageView1)
                } else {
                    GlideToVectorYou.justLoadImage(
                        context,
                        Uri.parse(list[position].iconUrl),
                        holder.ImageView
                    )
                }
                holder.tvName.text = list[position].name
                holder.tvSymbol.text = list[position].symbol
            }

        }

    }



    //STEP: 1
    //it all starts from here..!!
    //it checks the position
    //if the position is 5,10,15,20,25 --> then it gives the ItemViewType as R.layout.rvcoin_viewholder_right
    //else then it gives the ItemViewType as R.layout.rvcoin_viewholder_left
    //After this, we go to onCreateViewHolder
    override fun getItemViewType(position: Int): Int {
        //we do position + 1 because position starts at 0
        var lol = position+1
        return when (lol%5==0 && lol!=0) {
            true -> {
                R.layout.rvcoin_viewholder_right
            }
            false->{
                R.layout.rvcoin_viewholder_left
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolderLeft(view: View) : RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.textView_coinNameLeft)
        val ImageView1 = view.findViewById<ImageView>(R.id.imageView_coinImageLeft)
        val ImageView = view.findViewById<ImageView>(R.id.imageView_coinImageLeft)
        val tvSymbol = view.findViewById<TextView>(R.id.textView_coinAbbreviationLeft)

    }

    class ViewHolderRight(view: View,onItemClicked_fun:(Int)->Unit) : RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.textView_coinNameRight)
        val ImageView1 = view.findViewById<ImageView>(R.id.imageView_coinImageRight)
        val ImageView = view.findViewById<ImageView>(R.id.imageView_coinImageRight)
        val tvUrl = view.findViewById<TextView>(R.id.textView_coinUrlRight)

        //it passes adapterposition as an argument to be used later on
        //adapterposition will be saved as parameter when we create the viewholder at onCreateViewHolder
        //because to create the right viewholder, it requires 2 argument,
        //          1 is when you inflate the view,
        //          2 is the function (onItemClickedFun) which has adapterposition stored as parameter
        //since adapterposition and the index of element in "list" are the same, we just simply use adapterposition as index
        // like list[adapterposition]
        // notice that the parameter inside a lambda expression can also be written as 'it'
        // so  list[it] simply means list[adapterposition], why? because adapterposition was kept as parameter :) <3<3
        init {
            view.setOnClickListener {
                onItemClicked_fun(absoluteAdapterPosition)
            }

        }
    }
    


}