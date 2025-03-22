package com.example.myapplication3.view.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication3.domain.entities.Result
import com.example.myapplication3.domain.usecases.UpComingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpComingViewModel  @Inject constructor(
    private val  upComingUseCase : UpComingUseCase) : ViewModel() {


    private val _moviesState: MutableStateFlow<PagingData<Result>> = MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Result>> get() = _moviesState

    init {
        onEvent(UpComingEvent.GetUpComing)
        //processIntents()
    }
    fun onEvent(event: UpComingEvent) {
        viewModelScope.launch {
            when (event) {
                is UpComingEvent.GetUpComing -> {
                    getUpComingList()
                }
            }
        }
    }
    private suspend fun getUpComingList() {
       upComingUseCase.execute(Unit)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _moviesState.value = it
            }
    }

sealed class UpComingEvent {
    object GetUpComing : UpComingEvent()
}

data class UpComingState(val movies: List<Result> = listOf())}