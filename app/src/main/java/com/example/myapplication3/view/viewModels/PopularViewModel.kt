package com.example.myapplication3.view.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication3.domain.entities.Result
import com.example.myapplication3.domain.usecases.PopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(private val popularUseCase: PopularUseCase) : ViewModel() {


    private val _moviesState: MutableStateFlow<PagingData<Result>> = MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Result>> get() = _moviesState

    init {
        onEvent(HomeEvent.GetHome)
        //processIntents()
    }
    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.GetHome -> {
                    getPopularList()
                }
            }
        }
    }
    private suspend fun getPopularList() {
        popularUseCase.execute(Unit)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _moviesState.value = it
            }
    }
sealed class HomeEvent {
    object GetHome : HomeEvent()
}

data class HomeState(val movies: List<Result> = listOf())
}