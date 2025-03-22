package com.example.myapplication3.data.remote

import com.example.myapplication3.core.Constants
import com.example.myapplication3.domain.entities.NowPlayingResponse
import retrofit2.Response
import javax.inject.Inject


class MovieRemoteDataSourceImpl @Inject constructor(private val api: ProjectApiServices)
    : ProjectApiServices {
    override suspend fun getNowPlayingList(
        apiKey: String,
        pageNumber: Int): Response<NowPlayingResponse> {
          return api.getNowPlayingList(apiKey = Constants.API_KEY, pageNumber = Constants.MAX_PAGE_SIZE)

    }

    override suspend fun getPopularList(
        apiKey: String,
        pageNumber: Int): Response<NowPlayingResponse> {
        return api.getPopularList(apiKey = Constants.API_KEY, pageNumber = Constants.MAX_PAGE_SIZE)
    }

    override suspend fun getUpComingList(
        apiKey: String,
        pageNumber: Int
    ): Response<NowPlayingResponse> {
        return api.getUpComingList(apiKey = Constants.API_KEY, pageNumber = Constants.MAX_PAGE_SIZE)
    }


}