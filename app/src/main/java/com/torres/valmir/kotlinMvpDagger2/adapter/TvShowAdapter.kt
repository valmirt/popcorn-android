package com.torres.valmir.kotlinMvpDagger2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.model.TvShow

class TvShowAdapter (private var tvShows: ArrayList<TvShow>,
                     private var itemListener: ItemListener<TvShow>,
                     private var context: Context) : RecyclerView.Adapter<MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater.inflate(R.layout.item_movie_list, parent, false)
        return MovieViewHolder(noteView)
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val tv = tvShows[position]

        holder.fillDataTv(tv, context)
        holder.itemView.setOnClickListener {
            itemListener.onClick(tv)
        }
    }

    private fun clear(){
        this.tvShows.clear()
        notifyDataSetChanged()
    }

    fun replaceData(data: List<TvShow>) {
        clear()
        this.tvShows.addAll(data)
        notifyDataSetChanged()
    }

    fun addMoreItem(data: List<TvShow>) {
        var temp = this.tvShows.toList()
        clear()
        temp += data
        this.tvShows.addAll(temp)
        notifyDataSetChanged()
    }
}