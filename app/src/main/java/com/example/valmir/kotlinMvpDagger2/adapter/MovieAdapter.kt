package com.example.valmir.kotlinMvpDagger2.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.model.Movie

class MovieAdapter (private var movies: List<Movie>, private var itemListener: ItemListener<Movie>)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater.inflate(R.layout.item_movie_list, parent, false)
        return MovieViewHolder(noteView)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.populatingData(movie)
        holder.itemView.setOnClickListener {
            itemListener.onClick(movie)
        }
    }

    fun replaceData(data: List<Movie>) {
        this.movies = data
        notifyDataSetChanged()
    }

    fun addMoreItem(data: List<Movie>) {
        this.movies += data
        notifyDataSetChanged()
    }
}