package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.models.ShowDetails
import com.example.movieapp.models.TvShowResponse
import com.example.movieapp.models.TvShowResponseItem
import com.example.movieapp.network.RetrofitInstance
import com.example.movieapp.network.TvShowsApi
import retrofit2.Response
import javax.inject.Inject

class TvShowsRepository @Inject constructor() {

    suspend fun getShowsList(): Response<TvShowResponse>{
        return RetrofitInstance.api.getAllTvShows()
    }

    suspend fun getShow(id: Int): Response<ShowDetails>{
        return RetrofitInstance.api.getShowById(id)
    }
}