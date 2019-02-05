package com.torres.valmir.kotlin_mvp_dagger2.model

import com.google.gson.annotations.SerializedName

class ListTrailers (var code : Int = 0,
                    @SerializedName("results")
                    var trailerList: List<Trailer>? = null)