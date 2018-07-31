package com.example.valmir.kotlinMvpDagger2.ui.main.detail.info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.util.Constants.Companion.MOVIE_ID
import kotlinx.android.synthetic.main.fragment_info_detail.*
import java.math.RoundingMode
import java.text.DecimalFormat

class InfoFragment: Fragment() {
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments

        args?.let {
            movie = args.getSerializable(MOVIE_ID) as Movie
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fillView()
    }

    private fun fillView() {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        movie?.let { movie ->
            title_movie_detail.text = movie.title
            original_title_movie_detail.text = movie.originalTitle
            date_movie_detail.text = movie.releaseDate.subSequence(0, 4)
            time_movie_detail.text = movie.runtime.toString()
            vote_average_detail.text = df.format(movie.voteAverage)
            popularity_detail.text = df.format(movie.popularity)
            overview_detail.text = movie.overview
        }
    }
}