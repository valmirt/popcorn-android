package com.torres.valmir.kotlinMvpDagger2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.model.Season

class SeasonAdapter (private var seasons: List<Season>,
                     private var context: Context): RecyclerView.Adapter<SeasonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater.inflate(R.layout.item_season, parent, false)
        return SeasonViewHolder(noteView)
    }

    override fun getItemCount(): Int = seasons.size

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season = seasons[position]

        holder.fillData(season, context)
        holder.listenerInfo(season, context)
    }
}