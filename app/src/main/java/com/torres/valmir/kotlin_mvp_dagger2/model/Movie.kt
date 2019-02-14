package com.torres.valmir.kotlin_mvp_dagger2.model

import com.google.gson.annotations.SerializedName

class Movie (@SerializedName("release_date")
             var releaseDate: String = "",
             var runtime: Int = 0,
             var status: String = ""): Entity()