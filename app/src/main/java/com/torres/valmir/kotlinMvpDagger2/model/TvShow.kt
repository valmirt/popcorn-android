package com.torres.valmir.kotlinMvpDagger2.model

import com.google.gson.annotations.SerializedName

class TvShow (var name: String = "",
              @SerializedName("original_name")
              var originalName: String = "",
//              @SerializedName("episode_run_time")
//              var runtime: ,
              @SerializedName("first_air_date")
              var firstAirDate: String = "",
              var homepage: String? = "",
              @SerializedName("in_production")
              var inProduction: Boolean = false,
              @SerializedName("last_air_date")
              var lastAirDate: String = "",
              @SerializedName("number_of_episodes")
              var numberEpisodes: Int = 0,
              @SerializedName("number_of_seasons")
              var numberSeasons: Int = 0,
              var seasons: Season? = null): Entity()