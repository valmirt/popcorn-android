package com.example.valmir.kotlinMvpDagger2.adapter

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.util.Constants.Companion.BASE_URL_IMAGE_W185

class SimilarMoviesViewHolder(noteView: View): RecyclerView.ViewHolder(noteView) {
    private val imageView = noteView.findViewById<ImageView>(R.id.image_similar_movies)

    fun fillData(movie: Movie){
        var bitmap: Bitmap
        movie.posterUrl?.let {
            Glide.with(imageView.context)
                    .asBitmap()
                    .load(BASE_URL_IMAGE_W185+it)
                    .listener(object: RequestListener<Bitmap> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                            return false
                        }

                        override fun onResourceReady(resource: Bitmap?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            resource?.let {
                                bitmap = it
                                imageView.setImageBitmap(bitmap)
                            }
                            return true
                        }
                    })
                    .submit()
        }
    }
}