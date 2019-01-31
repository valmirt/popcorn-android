package com.torres.valmir.kotlin_mvp_dagger2.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants

class SimilarMoviesAdapter (private var movies: ArrayList<Movie>,
                            private var itemListener: ItemListener<Movie>,
                            private var context: Context)
    : RecyclerView.Adapter<SimilarMoviesAdapter.SimilarMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater.inflate(R.layout.item_similar_movies, parent, false)
        return SimilarMoviesViewHolder(noteView)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: SimilarMoviesViewHolder, position: Int) {
        val movie = movies[position]

        holder.fillData(movie, context)
        holder.itemView.setOnClickListener {
            itemListener.onClick(movie)
        }
    }

    fun clear(){
        this.movies.clear()
    }

    fun replaceData(data: List<Movie>) {
        clear()
        this.movies.addAll(data)
        notifyDataSetChanged()
    }

    fun addMoreItem(data: List<Movie>) {
        val temp = this.movies.toMutableList()
        clear()
        temp += data
        this.movies.addAll(temp)
        notifyDataSetChanged()
    }

    inner class SimilarMoviesViewHolder(noteView: View): RecyclerView.ViewHolder(noteView) {
        private val imageView = noteView.findViewById<ImageView>(R.id.image_similar_movies)

        fun fillData(movie: Movie, context: Context){
            var bitmap: Bitmap

            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_image_default_poster))

            movie.posterUrl?.let {poster ->
                Glide.with(imageView.context)
                        .asBitmap()
                        .load(Constants.BASE_URL_IMAGE_W185 +poster)
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

        fun fillDataTvShow(tv: TvShow, context: Context) {
            var bitmap: Bitmap

            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_image_default_poster))

            tv.posterUrl?.let {poster ->
                Glide.with(imageView.context)
                        .asBitmap()
                        .load(Constants.BASE_URL_IMAGE_W185 +poster)
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
}