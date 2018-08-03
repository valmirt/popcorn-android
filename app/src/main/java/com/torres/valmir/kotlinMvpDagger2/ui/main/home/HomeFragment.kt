package com.torres.valmir.kotlinMvpDagger2.ui.main.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.torres.valmir.kotlinMvpDagger2.adapter.ItemListener
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.TMDBApplication
import com.torres.valmir.kotlinMvpDagger2.adapter.MovieAdapter
import com.torres.valmir.kotlinMvpDagger2.model.Movie
import com.torres.valmir.kotlinMvpDagger2.ui.main.detail.DetailActivity
import com.torres.valmir.kotlinMvpDagger2.util.Constants
import com.torres.valmir.kotlinMvpDagger2.util.Constants.Companion.TYPE_LIST
import com.torres.valmir.kotlinMvpDagger2.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment: Fragment(), HomeContract.View {

    private lateinit var mListAdapter: MovieAdapter
    private var typeList = 0
    private var pagelist = 1
    private var query = ""
    private var pagelistQuery = 1
    private var language = ""
    private lateinit var preferences: SharedPreferences

    @Inject
    lateinit var mPresenter: HomeContract.Presenter

    private val itemListener = object : ItemListener<Movie>{
        override fun onClick(item: Movie) {
            mPresenter.getDetails(item.id, language)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mListAdapter = MovieAdapter(ArrayList(0), itemListener)

        TMDBApplication.graph.inject(this)
        mPresenter.attach(this)

        activity?.let {
            preferences = it.getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)
            language = preferences.getString(Constants.LANGUAGE, Constants.ENGLISH_LANGUAGE)
        }

        val args = arguments
        args?.let {
            typeList = it.getInt(TYPE_LIST)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        list_movie.setHasFixedSize(true)
        list_movie.layoutManager = LinearLayoutManager(context)
        list_movie.adapter = mListAdapter
        list_movie.addOnScrollListener(object : EndlessRecyclerViewScrollListener(list_movie.layoutManager as LinearLayoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (query.isEmpty()){
                    pagelist++
                    when(typeList){
                        1 -> mPresenter.getPopular(pagelist, language)
                        2 -> mPresenter.getTopRated(pagelist, language)
                        3 -> mPresenter.getNowPlaying(pagelist, language)
                    }
                }
                else {
                    pagelistQuery++
                    mPresenter.getMovie(query, pagelistQuery, language)
                }
            }
        })

        refresh_layout.setColorSchemeColors(
                ContextCompat.getColor(activity!!, R.color.colorPrimary),
                ContextCompat.getColor(activity!!, R.color.colorAccent),
                ContextCompat.getColor(activity!!, R.color.colorPrimaryDark))

        refresh_layout.setOnRefreshListener {
            when(typeList){
                1 -> mPresenter.getPopular(1, language)
                2 -> mPresenter.getTopRated(1, language)
                3 -> mPresenter.getNowPlaying(1, language)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            preferences = it.getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)
            language = preferences.getString(Constants.LANGUAGE, Constants.ENGLISH_LANGUAGE)
        }
        when(typeList){
            1 -> mPresenter.getPopular(1, language)
            2 -> mPresenter.getTopRated(1, language)
            3 -> mPresenter.getNowPlaying(1, language)
        }
    }

    override fun setLoading(isLoading: Boolean) {
        refresh_layout.isRefreshing = isLoading
    }

    override fun responseSuccessful(movieList: List<Movie>?) {
        movieList?.let { list ->
            mListAdapter.replaceData(list)
        }
    }

    override fun responseSucessfulMorePages(movieList: List<Movie>?) {
        movieList?.let { list ->
            mListAdapter.addMoreItem(list)
        }
    }

    override fun errorResponse(error: String) {
        Snackbar.make(view!!, error, Snackbar.LENGTH_LONG).show()
    }

    override fun responseDetail(movie: Movie) {
        mPresenter.swapActivity(activity, DetailActivity(), movie)
    }
}