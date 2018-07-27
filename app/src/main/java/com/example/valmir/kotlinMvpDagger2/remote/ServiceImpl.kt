package com.example.valmir.kotlinMvpDagger2.remote

import com.example.valmir.kotlinMvpDagger2.TMDBApplication
import com.example.valmir.kotlinMvpDagger2.model.ListMovies
import com.example.valmir.kotlinMvpDagger2.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ServiceImpl: ServiceApi {

    @Inject
    lateinit var retrofit: Retrofit

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun getMovie(callback: ServiceApi.ServiceCallback<ListMovies>, query: String, page: Int) {
        val callMovie = retrofit
                .create(EndPoint::class.java)
                .search(query = query,page = page)

        callMovie.enqueue(object: Callback<ListMovies>{
            override fun onFailure(call: Call<ListMovies>?, t: Throwable?) {
                callback.onLoaded(ListMovies())
            }

            override fun onResponse(call: Call<ListMovies>?, response: Response<ListMovies>?) {
                var result = ListMovies()
                response?.body()?.let {
                    result = it
                }
                response?.code()?.let {
                    result.code = it
                }
                callback.onLoaded(result)
            }
        })
    }

    override fun getPopular(callback: ServiceApi.ServiceCallback<ListMovies>, page: Int) {
        val callMovie = retrofit
                .create(EndPoint::class.java)
                .popular(page = page)

        callMovie.enqueue(object: Callback<ListMovies>{
            override fun onFailure(call: Call<ListMovies>?, t: Throwable?) {
                callback.onLoaded(ListMovies())
            }

            override fun onResponse(call: Call<ListMovies>?, response: Response<ListMovies>?) {
                var result = ListMovies()
                response?.body()?.let {
                    result = it
                }
                response?.code()?.let {
                    result.code = it
                }
                callback.onLoaded(result)
            }
        })
    }

    override fun getTopRated(callback: ServiceApi.ServiceCallback<ListMovies>, page: Int) {
        val callMovie = retrofit
                .create(EndPoint::class.java)
                .topRated(page = page)

        callMovie.enqueue(object: Callback<ListMovies>{
            override fun onFailure(call: Call<ListMovies>?, t: Throwable?) {
                callback.onLoaded(ListMovies())
            }

            override fun onResponse(call: Call<ListMovies>?, response: Response<ListMovies>?) {
                var result = ListMovies()
                response?.body()?.let {
                    result = it
                }
                response?.code()?.let {
                    result.code = it
                }
                callback.onLoaded(result)
            }
        })
    }

    override fun getUpComming(callback: ServiceApi.ServiceCallback<ListMovies>, page: Int) {
        val callMovie = retrofit
                .create(EndPoint::class.java)
                .upComing(page = page)

        callMovie.enqueue(object: Callback<ListMovies>{
            override fun onFailure(call: Call<ListMovies>?, t: Throwable?) {
                callback.onLoaded(ListMovies())
            }

            override fun onResponse(call: Call<ListMovies>?, response: Response<ListMovies>?) {
                var result = ListMovies()
                response?.body()?.let {
                    result = it
                }
                response?.code()?.let {
                    result.code = it
                }
                callback.onLoaded(result)
            }
        })
    }

    override fun getNowPlaying(callback: ServiceApi.ServiceCallback<ListMovies>, page: Int) {
        val callMovie = retrofit
                .create(EndPoint::class.java)
                .nowPlaying(page = page)

        callMovie.enqueue(object: Callback<ListMovies>{
            override fun onFailure(call: Call<ListMovies>?, t: Throwable?) {
                callback.onLoaded(ListMovies())
            }

            override fun onResponse(call: Call<ListMovies>?, response: Response<ListMovies>?) {
                var result = ListMovies()
                response?.body()?.let {
                    result = it
                }
                response?.code()?.let {
                    result.code = it
                }
                callback.onLoaded(result)
            }
        })
    }

    override fun getMovieId(callback: ServiceApi.ServiceCallback<Movie>, id: Int) {
        val callMovie = retrofit
                .create(EndPoint::class.java)
                .searchMovieId(id = id)

        callMovie.enqueue(object: Callback<Movie>{
            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                callback.onLoaded(Movie())
            }

            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                var result = Movie()
                response?.body()?.let {
                    result = it
                }
                response?.code()?.let {
                    result.code = it
                }
                callback.onLoaded(result)
            }
        })
    }
}