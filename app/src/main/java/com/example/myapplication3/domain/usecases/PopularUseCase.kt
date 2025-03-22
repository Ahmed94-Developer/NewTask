package com.example.myapplication3.domain.usecases
import androidx.paging.PagingData
import com.example.myapplication3.core.generic.BaseUseCase
import com.example.myapplication3.domain.entities.Result
import com.example.myapplication3.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularUseCase @Inject constructor(
    private val repository: MovieRepository) : BaseUseCase<Unit, Flow<PagingData<Result>>> {
    override suspend fun execute(input: Unit): Flow<PagingData<Result>> {
        return repository.getPopularList()
    }
}