package com.example.valmir.kotlinMvpDagger2.model

import com.google.gson.annotations.SerializedName

class ListCastCrew (var code : Int = 0,
                    @SerializedName("cast")
                    var castList: List<Cast>? = null)