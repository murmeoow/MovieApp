package com.example.movieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.ShowDetails
import com.example.movieapp.models.TvShowResponse
import com.example.movieapp.models.TvShowResponseItem
import com.example.movieapp.repository.TvShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(private val repository: TvShowsRepository): ViewModel() {

    val allShows : MutableLiveData<Response<TvShowResponse>> = MutableLiveData()
    val showDetails : MutableLiveData<Response<ShowDetails>> = MutableLiveData()

    private val _showClicked : MutableLiveData<Int> = MutableLiveData()
    val showClicked
        get() = _showClicked

    fun getTvShows() = viewModelScope.launch {
        allShows.value = repository.getShowsList()
    }

    fun onShowClicked(showId : Int){
        _showClicked.value = showId
    }

    fun getShowDetails(id: Int)= viewModelScope.launch {
        showDetails.value = repository.getShow(id)
    }


}