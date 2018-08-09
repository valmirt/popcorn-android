package com.torres.valmir.kotlinMvpDagger2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Season(@SerializedName("air_date")
             var airDate: String = "",
             @SerializedName("episode_count")
             var episodeCount: Int = 0,
             var id: Int = 0,
             var name: String = "",
             var overview: String = "",
             @SerializedName("poster_path")
             var posterPath: String? = "",
             @SerializedName("season_number")
             var seasonNumber: Int = 0): Serializable
