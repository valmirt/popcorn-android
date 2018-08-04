package com.torres.valmir.kotlinMvpDagger2.adapter

import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.model.Movie
import com.torres.valmir.kotlinMvpDagger2.util.Constants.Companion.BASE_URL_IMAGE_W185
import java.math.RoundingMode
import java.text.DecimalFormat

class MovieViewHolder (noteView: View): RecyclerView.ViewHolder(noteView) {
    private val imageItem = noteView.findViewById<ImageView>(R.id.image_item_movie)
    private val title = noteView.findViewById<TextView>(R.id.title_item_movie)
    private val originalTitle = noteView.findViewById<TextView>(R.id.original_title_item_movie)
    private val date = noteView.findViewById<TextView>(R.id.date_item_movie)
    private val popularity = noteView.findViewById<TextView>(R.id.popularity_item)
    private val voteAverage = noteView.findViewById<TextView>(R.id.vote_average_item)
    private val card = noteView.findViewById<CardView>(R.id.card_item_view)
    private val headerPopularity = noteView.findViewById<TextView>(R.id.header_popularity_item)
    private val headerVoteAverage = noteView.findViewById<TextView>(R.id.header_vote_average_item)

    fun fillData(movie: Movie){
        val df = DecimalFormat("#.##")
        val df2 = DecimalFormat("#.#")
        var bitmap: Bitmap
        df.roundingMode = RoundingMode.CEILING

        movie.posterUrl?.let {
            Glide.with(imageItem.context)
                    .asBitmap()
                    .load(BASE_URL_IMAGE_W185+it)
                    .listener(object: RequestListener<Bitmap>{
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                            return false
                        }

                        override fun onResourceReady(resource: Bitmap?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            resource?.let {
                                bitmap = it
                                imageItem.setImageBitmap(bitmap)
                                val palette = Palette.from(bitmap).generate()
                                val vibrant = palette.vibrantSwatch
                                vibrant?.let {
                                    card.setCardBackgroundColor(it.rgb)
                                    originalTitle.setTextColor(it.titleTextColor)
                                    title.setTextColor(it.titleTextColor)
                                    date.setTextColor(it.titleTextColor)
                                    popularity.setTextColor(it.titleTextColor)
                                    voteAverage.setTextColor(it.titleTextColor)
                                    headerPopularity.setTextColor(it.titleTextColor)
                                    headerVoteAverage.setTextColor(it.titleTextColor)
                                }
                            }
                            return true
                        }
                    })
                    .submit()
        }

        originalTitle.text = movie.originalTitle
        title.text = movie.title
        try{
            date.text = movie.releaseDate.subSequence(0, 4)
        } catch (e: Exception){}
        popularity.text = df2.format(movie.popularity)
        voteAverage.text = df.format(movie.voteAverage)
    }
}