package com.example.myapplication3.domain.repositories

import androidx.paging.PagingData
import com.example.myapplication3.data.remote.Resource
import com.example.myapplication3.domain.entities.NowPlayingResponse
import com.example.myapplication3.domain.entities.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlayingList() : Flow<PagingData<Result>>
    suspend fun getPopularList() : Flow<PagingData<Result>>
    suspend fun getUpComingList() : Flow<PagingData<Result>>
}