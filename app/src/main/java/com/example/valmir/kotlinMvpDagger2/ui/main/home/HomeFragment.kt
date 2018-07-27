package com.example.valmir.kotlinMvpDagger2.ui.main.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.valmir.kotlinMvpDagger2.Adapter.ItemListener
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.TMDBApplication
import com.example.valmir.kotlinMvpDagger2.Adapter.MovieAdapter
import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment: Fragment(), HomeContract.View {

    private lateinit var mListAdapter: MovieAdapter

    @Inject
    lateinit var mPresenter: HomeContract.Presenter

    private val itemListener = object : ItemListener<Movie>{
        override fun onClick(item: Movie) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mListAdapter = MovieAdapter(ArrayList<Movie>(0), itemListener)

        TMDBApplication.graph.inject(this)
        mPresenter.attach(this)

        //Este fragmento escuta a toolbar da atividade
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
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        refresh_layout.setColorSchemeColors(
                ContextCompat.getColor(activity!!, R.color.colorPrimary),
                ContextCompat.getColor(activity!!, R.color.colorAccent),
                ContextCompat.getColor(activity!!, R.color.colorPrimaryDark))

        refresh_layout.setOnRefreshListener {
            TODO("not implemented")
        }
    }


    override fun setLoading(isLoading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun responseSuccessful() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun errorResponse(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun movieDetails(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}