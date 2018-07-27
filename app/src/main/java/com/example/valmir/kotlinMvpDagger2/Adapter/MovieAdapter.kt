package com.example.valmir.kotlinMvpDagger2.Adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.valmir.kotlinMvpDagger2.model.Movie

class MovieAdapter (private var moview: List<Movie>, private var itemListener: ItemListener<Movie>):
        RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}