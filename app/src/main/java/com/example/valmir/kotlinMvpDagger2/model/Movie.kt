package com.example.valmir.kotlinMvpDagger2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Movie (var code: Int = 0,
             var id: Int = 0,
             var title: String = "",
             @SerializedName("original_title")
             var originalTitle: String = "",
             @SerializedName("poster_path")
             var posterUrl: String? = null,
             @SerializedName("backdrop_path")
             var posterDetail: String? = null,
             @SerializedName("genres")
             var genres: List<Genres>? = null,
             @SerializedName("original_language")
             var originalLanguage: String = "",
             var overview: String = "",
             var popularity: Double = 0.0,
             @SerializedName("release_date")
             var releaseDate: String = "",
             var runtime: Int = 0,
             @SerializedName("vote_average")
             var voteAverage: Double = 0.0,
             @SerializedName("production_companies")
             var production: List<Production>? = null,
             var status: String = ""): Serializable