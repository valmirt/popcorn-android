package com.torres.valmir.kotlinMvpDagger2.ui.main.detail.info

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.TMDBApplication
import com.torres.valmir.kotlinMvpDagger2.adapter.ItemListener
import com.torres.valmir.kotlinMvpDagger2.adapter.SimilarMoviesAdapter
import com.torres.valmir.kotlinMvpDagger2.model.Movie
import com.torres.valmir.kotlinMvpDagger2.ui.main.detail.DetailActivity
import com.torres.valmir.kotlinMvpDagger2.util.Constants
import com.torres.valmir.kotlinMvpDagger2.util.Constants.Companion.MOVIE_OBJECT
import com.torres.valmir.kotlinMvpDagger2.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_info_detail.*
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class InfoFragment: Fragment(), InfoContract.View {
    private var movie: Movie? = null
    private lateinit var mListAdapter: SimilarMoviesAdapter
    private var pagelist = 1
    private var language = ""
    private lateinit var preferences: SharedPreferences

    @Inject
    lateinit var mPresenter: InfoContract.Presenter

    private val itemListener = object : ItemListener<Movie> {
        override fun onClick(item: Movie) {
            mPresenter.getDetails(item.id, language)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TMDBApplication.graph.inject(this)
        mPresenter.attach(this)
        val args = arguments

        mListAdapter = SimilarMoviesAdapter(ArrayList(0), itemListener, context!!)

        activity?.let {
            preferences = it.getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)
            language = preferences.getString(Constants.LANGUAGE, Constants.ENGLISH_LANGUAGE)
        }

        args?.let {
            movie = it.getSerializable(MOVIE_OBJECT) as Movie
            mPresenter.getSimilarMovies(movie!!.id, 1, language)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fillView()

        list_similar_movies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        list_similar_movies.setHasFixedSize(true)
        list_similar_movies.isNestedScrollingEnabled = false
        list_similar_movies.adapter = mListAdapter
        list_similar_movies.addOnScrollListener(object : EndlessRecyclerViewScrollListener(list_similar_movies.layoutManager as LinearLayoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                movie?.let {
                    pagelist++
                    mPresenter.getSimilarMovies(it.id, pagelist, language)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            preferences = it.getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)
            language = preferences.getString(Constants.LANGUAGE, Constants.ENGLISH_LANGUAGE)
        }
        movie?.let {
            mPresenter.getSimilarMovies(it.id, 1, language)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillView() {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        movie?.let { movie ->
            title_movie_detail.text = movie.title
            original_title_movie_detail.text = movie.originalTitle

            try {
                date_movie_detail.text = "("+movie.releaseDate.subSequence(0, 4)+")"
            } catch (e: Exception){}

            time_movie_detail.text = movie.runtime.toString()
            vote_average_detail.text = df.format(movie.voteAverage)
            popularity_detail.text = df.format(movie.popularity)
            overview_detail.text = movie.overview
            movie.genres?.let { genres ->
                for (genre in genres){
                    val temp = genre_movie_detail.text.toString()
                    genre_movie_detail.text = temp+genre.name+"\n"
                }
            }
            movie.production?.let { productions ->
                for (production in productions){
                    val temp = production_movie_detail.text.toString()
                    production_movie_detail.text = temp+production.name+"\n"
                }
            }
        }
    }

    override fun successResponse(similarMovies: List<Movie>?) {
        similarMovies?.let { list ->
            mListAdapter.replaceData(list)
        }
    }

    override fun successResponseMorePages(similarMovies: List<Movie>?) {
        similarMovies?.let { list ->
            mListAdapter.addMoreItem(list)
        }
    }

    override fun errorResponse(error: String) = Snackbar.make(view!!, error, Snackbar.LENGTH_LONG).show()

    override fun responseDetail(movie: Movie) {
        mPresenter.swapActivity(DetailActivity(), movie)
    }

}