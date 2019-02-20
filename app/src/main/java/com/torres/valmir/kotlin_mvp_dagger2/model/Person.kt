package com.torres.valmir.kotlin_mvp_dagger2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Person (var code: Int = 0,
              var id: Int = 0,
              var birthday: String? = "",
              var deathday: String? = "",
              @SerializedName("know_for_department")
              var knowDepartment: String? = "",
              var name: String? = "",
              var gender: Int = 0,
              var biography: String? = "",
              @SerializedName("place_of_birth")
              var placeBirth: String? = "",
              @SerializedName("profile_path")
              var profilePath: String? = ""): Serializable