package com.torres.valmir.kotlinMvpDagger2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.model.Season
import com.torres.valmir.kotlinMvpDagger2.util.Constants

class SeasonViewHolder(noteview: View): RecyclerView.ViewHolder(noteview) {
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