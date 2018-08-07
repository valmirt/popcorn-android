package com.torres.valmir.kotlinMvpDagger2.model

import com.google.gson.annotations.SerializedName

class Movie (var title: String = "",
             @SerializedName("original_title")
             var originalTitle: String = "",
             @SerializedName("release_date")
             var releaseDate: String = "",
             var runtime: Int = 0,
             var status: String = ""): Entity()