package com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.DetailActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.TYPE_LIST
import com.torres.valmir.kotlin_mvp_dagger2.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject
import android.view.*
import com.torres.valmir.kotlin_mvp_dagger2.adapter.*
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseFragment


class ListFragment: BaseFragment(), ListContract.View {

    private lateinit var mListAdapter: MovieAdapter
    private lateinit var mListAdapterTv: TvShowAdapter
    private var typeList = 0
    private var pagelist = 1
    private var query = ""
    private var pagelistQuery = 1
    private var language = ""
    private lateinit var preferences: SharedPreferences

    @Inject
    lateinit var mPresenter: ListContract.Presenter

    private val itemListenerMovie = object : ItemListener<Movie>{
        override fun onClick(item: Movie) {
            mPresenter.getDetailsMovie(item.id, language)
        }
    }

    private val itemListenerTv = object : ItemListener<TvShow> {
        override fun onClick(item: TvShow) {
            mPresenter.getDetailsTvShow(item.id, language)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mListAdapter = MovieAdapter(ArrayList(0), itemListenerMovie, context!!)
        mListAdapterTv = TvShowAdapter(ArrayList(0), itemListenerTv, context!!)

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
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list_movie.setHasFixedSize(true)
        list_movie.layoutManager = LinearLayoutManager(context)
        if (typeList < 4) list_movie.adapter = mListAdapter
        else list_movie.adapter = mListAdapterTv

        list_movie.addOnScrollListener(object : EndlessRecyclerViewScrollListener(list_movie.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (query.isEmpty()) {
                    pagelist++
                    when (typeList) {
                        1 -> mPresenter.getPopularMovie(pagelist, language)
                        2 -> mPresenter.getTopRatedMovie(pagelist, language)
                        3 -> mPresenter.getNowPlayingMovie(pagelist, language)
                        4 -> mPresenter.getPopularTvShow(pagelist, language)
                        5 -> mPresenter.getTopRatedTvShow(pagelist, language)
                        6 -> mPresenter.getTodaysTvShow(pagelist, language)
                    }
                } else {
                    pagelistQuery++
                    if (typeList < 4) mPresenter.getMovie(query, pagelistQuery, language)
                    else mPresenter.getTvShow(query, pagelistQuery, language)
                }
            }
        })

        refresh_layout.setColorSchemeColors(
                ContextCompat.getColor(activity!!, R.color.colorPrimary),
                ContextCompat.getColor(activity!!, R.color.colorAccent),
                ContextCompat.getColor(activity!!, R.color.colorPrimaryDark))

        refresh_layout.setOnRefreshListener {
            updateList()
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            preferences = it.getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)
            language = preferences.getString(Constants.LANGUAGE, Constants.ENGLISH_LANGUAGE)
        }
        updateList()
    }

    override fun setLoading(isLoading: Boolean) {
        refresh_layout.isRefreshing = isLoading
    }

    override fun successResponseMovie(movieList: List<Movie>?) {
        movieList?.let { list ->
            mListAdapter.replaceData(list)
        }
    }

    override fun successResponseTvShow(tvList: List<TvShow>?) {
        tvList?.let { list ->
            mListAdapterTv.replaceData(list)
        }
    }

    override fun successResponseMorePagesMovie(movieList: List<Movie>?) {
        movieList?.let { list ->
            mListAdapter.addMoreItem(list)
        }
    }

    override fun successResponseMorePagesTvShow(tvList: List<TvShow>?) {
        tvList?.let { list ->
            mListAdapterTv.addMoreItem(list)
        }
    }

    override fun errorResponse(error: String) {
        Snackbar.make(view!!, error, Snackbar.LENGTH_LONG).show()
    }

    override fun successResponseDetailTvShow(tv: TvShow) {
        mPresenter.swapActivity(activity, DetailActivity(), null, tv)
    }

    override fun successResponseDetailMovie(movie: Movie) {
        mPresenter.swapActivity(activity, DetailActivity(), movie, null)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        val mSearchMenuItem = menu?.findItem(R.id.action_search)
        val searchView = mSearchMenuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    this@ListFragment.query = it
                    if (typeList < 4) mPresenter.getMovie(it, 1, language)
                    else mPresenter.getTvShow(it, 1, language)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        mSearchMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                this@ListFragment.query = ""
                this@ListFragment.pagelistQuery = 1
                updateList()
                return true
            }
        })

    }

    private fun updateList(){
        if (query.isEmpty()) {
            when (typeList) {
                1 -> mPresenter.getPopularMovie(pagelist, language)
                2 -> mPresenter.getTopRatedMovie(pagelist, language)
                3 -> mPresenter.getNowPlayingMovie(pagelist, language)
                4 -> mPresenter.getPopularTvShow(pagelist, language)
                5 -> mPresenter.getTopRatedTvShow(pagelist, language)
                6 -> mPresenter.getTodaysTvShow(pagelist, language)
            }
        } else {
            if (typeList < 4) mPresenter.getMovie(query, 1, language)
            else mPresenter.getTvShow(query, 1, language)
        }
    }
}