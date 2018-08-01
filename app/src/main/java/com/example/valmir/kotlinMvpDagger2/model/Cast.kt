package com.example.valmir.kotlinMvpDagger2.model

import com.google.gson.annotations.SerializedName

class Cast (var order: Int = 0,
            var name: String = "",
            var character: String = "",
            @SerializedName("profile_path")
            var profilePath: String? = null)