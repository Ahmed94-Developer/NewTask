package com.example.myapplication3.domain.entities


data class NowPlaying (

     var dates        : Dates?             = Dates(),
     var page         : Int?               = null,
     var results      : List<Results> = arrayListOf(),
     var totalPages   : Int?               = null,
     var totalResults : Int?               = null

)