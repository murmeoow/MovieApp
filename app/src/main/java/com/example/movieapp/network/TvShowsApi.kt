package com.example.movieapp.network

import com.example.movieapp.models.ShowDetails
import com.example.movieapp.models.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TvShowsApi {

    @GET("shows")
    suspend fun getAllTvShows(): Response<TvShowResponse>

    @GET("shows/{showId}")
    suspend fun getShowById(
        @Path("showId") showId: Int
    ): Response<ShowDetails>

}