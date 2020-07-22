package com.jemy.hiltapp.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jemy.hiltapp.model.Blog
import com.jemy.hiltapp.repository.MainRepository
import com.jemy.hiltapp.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) :ViewModel() {

    private val _dataState = MutableLiveData<DataState<List<Blog>>>()

    val dataState : LiveData<DataState<List<Blog>>>
    get() = _dataState

    @ExperimentalCoroutinesApi
    fun setStatEvent(mainStatEvent: MainStatEvent){
        viewModelScope.launch {
            when(mainStatEvent) {
                 is MainStatEvent.GetBlogEvents -> {
                     mainRepository.getBlog()
                         .onEach { dataState ->
                             _dataState.value = dataState
                         }
                         .launchIn(viewModelScope)
                 }
                is MainStatEvent.None -> {
                    // I don't care -_-
                }
            }
        }
    }
}

sealed class MainStatEvent {

    object GetBlogEvents : MainStatEvent()

    object None : MainStatEvent()
}