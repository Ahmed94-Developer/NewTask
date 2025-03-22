package com.example.myapplication3.data.remote
import com.example.myapplication3.domain.entities.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjectApiServices {

    @GET("now_playing")
    suspend fun getNowPlayingList(
        @Query("api_key") apiKey : String,
        @Query("page") pageNumber: Int) : Response<NowPlayingResponse>

    @GET("popular")
    suspend fun getPopularList(
        @Query("api_key") apiKey : String,
        @Query("page") pageNumber: Int) : Response<NowPlayingResponse>

    @GET("upcoming")
    suspend fun getUpComingList(
        @Query("api_key") apiKey : String,
        @Query("page") pageNumber: Int) : Response<NowPlayingResponse>

}