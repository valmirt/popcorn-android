package com.torres.valmir.kotlin_mvp_dagger2.model

import com.google.gson.annotations.SerializedName

class ListEntity (var code : Int = 0,
                  @SerializedName("cast")
                  var entityList: List<Entity>? = null)