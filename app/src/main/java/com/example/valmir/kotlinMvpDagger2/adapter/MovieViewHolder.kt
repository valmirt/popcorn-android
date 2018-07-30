package com.example.valmir.kotlinMvpDagger2.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.graphics.Palette
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.util.Constants.Companion.BASE_URL_IMAGE
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
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

    fun populatingData(movie: Movie){
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        movie.posterUrl?.let {
            Picasso.with(imageItem.context)
                    .load(BASE_URL_IMAGE+it)
                    .placeholder(R.drawable.ic_image_default_poster)
                    .into(object : Target{
                        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                        override fun onBitmapFailed(errorDrawable: Drawable?) {}

                        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                            imageItem.setImageBitmap(bitmap)
                            val palette = Palette.from(bitmap!!).generate()
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
                    })
        }

        originalTitle.text = movie.originalTitle
        title.text = movie.title
        date.text = movie.releaseDate.subSequence(0, 4)
        popularity.text = df.format(movie.popularity)
        voteAverage.text = df.format(movie.voteAverage)
    }
}