package com.torres.valmir.kotlinMvpDagger2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.model.Cast

class CastingAdapter (private var casting: ArrayList<Cast>,
                      private var context: Context) : RecyclerView.Adapter<CastingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastingViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater.inflate(R.layout.item_casting, parent, false)
        return CastingViewHolder(noteView)
    }

    override fun getItemCount(): Int = casting.size

    override fun onBindViewHolder(holder: CastingViewHolder, position: Int) {
        val cast = casting[position]
        holder.fillData(cast, context)
    }

    fun clear(){
        this.casting.clear()
        notifyDataSetChanged()
    }

    fun replaceData(data: List<Cast>){
        clear()
        this.casting.addAll(data)
        notifyDataSetChanged()
    }
}