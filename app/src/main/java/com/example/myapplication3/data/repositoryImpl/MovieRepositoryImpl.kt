package com.example.myapplication3.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication3.core.Constants
import com.example.myapplication3.data.remote.ProjectApiServices
import com.example.myapplication3.data.repositoryImpl.paging.NowPlayingPagingResource
import com.example.myapplication3.data.repositoryImpl.paging.PopularPagingResource
import com.example.myapplication3.data.repositoryImpl.paging.UpComingPagingResource
import com.example.myapplication3.domain.entities.Result
import com.example.myapplication3.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private  val projectApiServices: ProjectApiServices)
    : MovieRepository {

    override suspend fun getNowPlayingList(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                NowPlayingPagingResource(projectApiServices)
            }
        ).flow
    }

    override suspend fun getPopularList(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                PopularPagingResource(projectApiServices)
            }
        ).flow
    }

    override suspend fun getUpComingList(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                UpComingPagingResource(projectApiServices)
            }
        ).flow
    }


}
