package com.example.movieapp.models

data class ShowDetails(

    val ended: String,
    val genres: List<String>,
    val id: Int,
    val image: ImageX,
    val language: String,
    val name: String,
    val premiered: String,
    val rating: Rating,
    val summary: String,

)