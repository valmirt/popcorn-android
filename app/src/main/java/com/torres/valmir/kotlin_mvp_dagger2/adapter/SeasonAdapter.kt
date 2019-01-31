package com.torres.valmir.kotlin_mvp_dagger2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.model.Season
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants

class SeasonAdapter (private var seasons: List<Season>,
                     private var context: Context): RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>() {

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

    inner class SeasonViewHolder(noteview: View): RecyclerView.ViewHolder(noteview) {
        private var poster = noteview.findViewById<ImageView>(R.id.image_season)
        private var title = noteview.findViewById<TextView>(R.id.title_season)
        private var text = noteview.findViewById<TextView>(R.id.tt_season)
        private var info = noteview.findViewById<ImageView>(R.id.info_season)

        @SuppressLint("SetTextI18n")
        fun fillData(data: Season, context: Context){
            var bitmap: Bitmap
            poster.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_image_default_poster))
            title.text = ""
            data.posterPath?.let { p->
                Glide.with(poster.context)
                        .asBitmap()
                        .load(Constants.BASE_URL_IMAGE_W185+p)
                        .listener(object: RequestListener<Bitmap> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                                return false
                            }

                            override fun onResourceReady(resource: Bitmap?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                resource?.let {
                                    bitmap = it
                                    poster.setImageBitmap(bitmap)
                                }
                                return true
                            }
                        })
                        .submit()
            }

            text.text = context.getString(R.string.season_number)+" "+data.seasonNumber
            title.text = data.name
        }

        fun listenerInfo(data: Season, context: Context){
            info.setOnClickListener{
                val builder = android.support.v7.app.AlertDialog.Builder(context)

                builder.setTitle(data.name)
                builder.setMessage(data.overview)
                builder.setNegativeButton(context.getString(R.string.colse)) { _, _ -> }
                builder.create()
                builder.show()
            }
        }
    }
}