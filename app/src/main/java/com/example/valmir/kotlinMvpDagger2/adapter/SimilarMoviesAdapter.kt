package com.example.valmir.kotlinMvpDagger2.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.model.Movie

class SimilarMoviesAdapter (private var movies: List<Movie>, private var itemListener: ItemListener<Movie>)
    : RecyclerView.Adapter<SimilarMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater.inflate(R.layout.item_similar_movies, parent, false)
        return SimilarMoviesViewHolder(noteView)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: SimilarMoviesViewHolder, position: Int) {
        val movie = movies[position]

        holder.fillData(movie)
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