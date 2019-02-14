package com.torres.valmir.kotlin_mvp_dagger2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.ViewGroup
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow

class TvShowAdapter (private val itemListener: ItemListener<TvShow>,
                     private val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private var tvShows = ArrayList<TvShow>(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater.inflate(R.layout.item_movie_list, parent, false)
        return MovieAdapter.MovieViewHolder(noteView)
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val tv = tvShows[position]

        holder.fillDataTv(tv, context)
        holder.itemView.setOnClickListener {
            itemListener.onClick(tv)
        }
    }

    fun replaceData(data: List<TvShow>) {
        this.tvShows.clear()
        this.tvShows.addAll(data)
        notifyDataSetChanged()
    }

    fun addMoreItem(data: List<TvShow>) {
        this.tvShows.addAll(data)
        notifyDataSetChanged()
    }
}