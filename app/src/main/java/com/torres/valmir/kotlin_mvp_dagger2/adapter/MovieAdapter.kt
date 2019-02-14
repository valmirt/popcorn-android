package com.torres.valmir.kotlin_mvp_dagger2.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.method.TextKeyListener.clear
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
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import java.math.RoundingMode
import java.text.DecimalFormat

class MovieAdapter (private val itemListener: ItemListener<Movie>,
                    private val context: Context)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies = ArrayList<Movie>(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater.inflate(R.layout.item_movie_list, parent, false)
        return MovieViewHolder(noteView)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.fillData(movie, context)
        holder.itemView.setOnClickListener {
            itemListener.onClick(movie)
        }
    }

    fun replaceData(data: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(data)
        notifyDataSetChanged()
    }

    fun addMoreItem(data: List<Movie>) {
        this.movies.addAll(data)
        notifyDataSetChanged()
    }

    open class MovieViewHolder (noteView: View): RecyclerView.ViewHolder(noteView) {
        private val imageItem = noteView.findViewById<ImageView>(R.id.image_item_movie)
        private val title = noteView.findViewById<TextView>(R.id.title_item_movie)
        private val originalTitle = noteView.findViewById<TextView>(R.id.original_title_item_movie)
        private val date = noteView.findViewById<TextView>(R.id.date_item_movie)
        private val popularity = noteView.findViewById<TextView>(R.id.popularity_item)
        private val voteAverage = noteView.findViewById<TextView>(R.id.vote_average_item)
        private val card = noteView.findViewById<CardView>(R.id.card_item_view)
        private val headerPopularity = noteView.findViewById<TextView>(R.id.header_popularity_item)
        private val headerVoteAverage = noteView.findViewById<TextView>(R.id.header_vote_average_item)

        fun fillData(movie: Movie, context: Context){
            val df = DecimalFormat("#.##")
            val df2 = DecimalFormat("#.#")
            var bitmap: Bitmap

            imageItem.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_image_default_poster))
            title.text = ""
            title.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            originalTitle.text = ""
            originalTitle.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            date.text = ""
            date.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            popularity.text = ""
            popularity.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            voteAverage.text = ""
            voteAverage.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.defaultCardView))
            headerPopularity.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            headerVoteAverage.setTextColor(ContextCompat.getColor(context, R.color.colorText))

            df.roundingMode = RoundingMode.CEILING

            movie.posterUrl?.let { poster->
                Glide.with(imageItem.context)
                        .asBitmap()
                        .load(Constants.BASE_URL_IMAGE_W185 +poster)
                        .listener(object: RequestListener<Bitmap> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                                return false
                            }

                            override fun onResourceReady(resource: Bitmap?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                resource?.let { bp ->
                                    bitmap = bp
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

        fun fillDataTv(tv: TvShow, context: Context) {
            val df = DecimalFormat("#.##")
            val df2 = DecimalFormat("#.#")
            var bitmap: Bitmap

            imageItem.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_image_default_poster))
            title.text = ""
            title.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            originalTitle.text = ""
            originalTitle.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            date.text = ""
            date.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            popularity.text = ""
            popularity.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            voteAverage.text = ""
            voteAverage.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.defaultCardView))
            headerPopularity.setTextColor(ContextCompat.getColor(context, R.color.colorText))
            headerVoteAverage.setTextColor(ContextCompat.getColor(context, R.color.colorText))

            df.roundingMode = RoundingMode.CEILING

            tv.posterUrl?.let { poster->
                Glide.with(imageItem.context)
                        .asBitmap()
                        .load(Constants.BASE_URL_IMAGE_W185 +poster)
                        .listener(object: RequestListener<Bitmap> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                                return false
                            }

                            override fun onResourceReady(resource: Bitmap?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                resource?.let { bp ->
                                    bitmap = bp
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

            originalTitle.text = tv.originalName
            title.text = tv.name
            try{
                date.text = tv.firstAirDate.subSequence(0, 4)
            } catch (e: Exception){}
            popularity.text = df2.format(tv.popularity)
            voteAverage.text = df.format(tv.voteAverage)
        }
    }
}