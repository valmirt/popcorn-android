package com.torres.valmir.kotlin_mvp_dagger2.model

import com.google.gson.annotations.SerializedName

class ListMovies (var code : Int = 0,
                  @SerializedName("total_pages")
                  var totalPages: Int = 0,
                  var page: Int = 0,
                  @SerializedName("results")
                  var movieList: List<Movie>? = null)