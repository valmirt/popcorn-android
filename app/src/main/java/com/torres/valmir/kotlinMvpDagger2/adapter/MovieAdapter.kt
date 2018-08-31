package com.torres.valmir.kotlinMvpDagger2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.model.Movie

class MovieAdapter (private var movies: ArrayList<Movie>,
                    private var itemListener: ItemListener<Movie>,
                    private var context: Context)
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

        holder.fillData(movie, context)
        holder.itemView.setOnClickListener {
            itemListener.onClick(movie)
        }
    }

    fun clear(){
        this.movies.clear()
        notifyDataSetChanged()
    }

    fun replaceData(data: List<Movie>) {
        clear()
        this.movies.addAll(data)
        notifyDataSetChanged()
    }

    fun addMoreItem(data: List<Movie>) {
        var temp = this.movies.toList()
        clear()
        temp += data
        this.movies.addAll(temp)
        notifyDataSetChanged()
    }
}