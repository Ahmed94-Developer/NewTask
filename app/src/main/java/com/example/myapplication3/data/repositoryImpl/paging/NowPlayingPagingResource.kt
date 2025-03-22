package com.example.myapplication3.data.repositoryImpl.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication3.core.Constants
import com.example.myapplication3.data.mapper.mapFromListModel
import com.example.myapplication3.data.remote.ProjectApiServices
import retrofit2.HttpException
import java.io.IOException

import com.example.myapplication3.domain.entities.Result

class NowPlayingPagingResource(
    private val projectApiServices: ProjectApiServices, ) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {

            val currentPage = params.key ?: 1
            val movies = projectApiServices.getNowPlayingList(
                apiKey = Constants.API_KEY,
                pageNumber = currentPage
            )
            LoadResult.Page(
                data = movies.body()!!.results.mapFromListModel(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.body()!!.results.isEmpty()) null else movies.body()!!.page!! + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

}