package com.torres.valmir.kotlinMvpDagger2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.torres.valmir.kotlinMvpDagger2.model.TvShow

class TvShowAdapter (private var movies: ArrayList<TvShow>,
                     private var itemListener: ItemListener<TvShow>,
                     private var context: Context) : RecyclerView.Adapter<TvShowViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}