package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.info

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.adapter.ItemListener
import com.torres.valmir.kotlin_mvp_dagger2.adapter.SimilarMoviesAdapter
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.DetailActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.MOVIE_OBJECT
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.TVSHOW_OBJECT
import com.torres.valmir.kotlin_mvp_dagger2.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_info_detail.*
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class InfoFragment: BaseFragment(), InfoContract.View {
    private var movie: Movie? = null
    private var tv: TvShow? = null
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
        mPresenter.attach(this)
        val args = arguments

        mListAdapter = SimilarMoviesAdapter(ArrayList(0), itemListener, context!!)

        activity?.let {
            preferences = it.getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)
            language = preferences.getString(Constants.LANGUAGE, Constants.ENGLISH_LANGUAGE)
        }

        args?.let {
            movie = it.getSerializable(MOVIE_OBJECT) as? Movie
            tv = it.getSerializable(TVSHOW_OBJECT) as? TvShow
            movie?.let { mv -> mPresenter.getSimilarMovies(mv.id, 1, language) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fillView()
        movie?.let {
            list_similar_movies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            list_similar_movies.setHasFixedSize(true)
            list_similar_movies.isNestedScrollingEnabled = false
            list_similar_movies.adapter = mListAdapter
            list_similar_movies.addOnScrollListener(object : EndlessRecyclerViewScrollListener(list_similar_movies.layoutManager as LinearLayoutManager){
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    movie?.let { mv->
                        pagelist++
                        mPresenter.getSimilarMovies(mv.id, pagelist, language)
                    }
                }
            })
        }
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
            layout_tv.visibility = View.GONE
            in_production.visibility = View.GONE

            try {
                date_movie_detail.text = "("+movie.releaseDate.subSequence(0, 4)+")\n"
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
        tv?.let { tv ->
            layout_similar_movies.visibility = View.GONE
            title_movie_detail.text = tv.name
            original_title_movie_detail.text = tv.originalName

            try {
                date_movie_detail.text = "("+tv.firstAirDate.subSequence(0, 4)+")"
            } catch (e: Exception){}

            number_seasons.text = tv.numberSeasons.toString()+" "+getString(R.string.season)
            number_episodes.text = " "+tv.numberEpisodes.toString()+" "+getString(R.string.episodes)

            tv.runtime?.let { runtime ->
                for (time in runtime){
                    time_movie_detail.text = time_movie_detail.text.toString()+time.toString()+"~"
                }
                time_movie_detail.text = time_movie_detail.text.toString().removeSuffix("~")
            }

            if (tv.inProduction) in_production.text = getString(R.string.in_production)+"\n"
            else in_production.text = getString(R.string.finished)+"\n"

            vote_average_detail.text = df.format(tv.voteAverage)
            popularity_detail.text = df.format(tv.popularity)
            overview_detail.text = tv.overview
            tv.genres?.let { genres ->
                for (genre in genres){
                    val temp = genre_movie_detail.text.toString()
                    genre_movie_detail.text = temp+genre.name+"\n"
                }
            }
            tv.production?.let { productions ->
                for (production in productions){
                    val temp = production_movie_detail.text.toString()
                    production_movie_detail.text = temp+production.name+"\n"
                }
            }
        }
    }

    override fun successResponseMovie(similarMovies: List<Movie>?) {
        similarMovies?.let { list ->
            mListAdapter.replaceData(list)
        }
    }

    override fun successResponseMorePagesMovie(similarMovies: List<Movie>?) {
        similarMovies?.let { list ->
            mListAdapter.addMoreItem(list)
        }
    }

    override fun errorResponse(error: String) = Snackbar.make(view!!, error, Snackbar.LENGTH_LONG).show()

    override fun responseDetailMovie(movie: Movie) {
        mPresenter.swapActivity(DetailActivity(), movie, null)
    }
}