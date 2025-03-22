package com.example.myapplication3.domain.entities

import com.google.gson.annotations.SerializedName


data class NowPlayingResponse (

  @SerializedName("dates") var dates        : Dates?             = Dates(),
  @SerializedName("page") var page         : Int?               = null,
  @SerializedName("results") var results      : List<Results> = arrayListOf(),
  @SerializedName("total_pages") var totalPages   : Int?               = null,
  @SerializedName("total_results") var totalResults : Int?               = null

)