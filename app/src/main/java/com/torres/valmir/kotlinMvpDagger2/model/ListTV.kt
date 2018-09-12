package com.torres.valmir.kotlinMvpDagger2.model

import com.google.gson.annotations.SerializedName

class ListTV (var code : Int = 0,
              @SerializedName("total_pages")
              var totalPages: Int = 0,
              var page: Int = 0,
              @SerializedName("results")
              var tvList: List<TvShow>? = null)