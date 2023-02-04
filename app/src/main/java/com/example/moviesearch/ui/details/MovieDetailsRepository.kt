package com.example.moviesearch.ui.details

import com.example.moviesearch.data.moviedetails.MovieDetails
import com.example.moviesearch.remote.MovieInterface
import com.example.moviesearch.utils.Constants
import com.example.moviesearch.utils.Results
import com.example.moviesearch.utils.Status

class MovieDetailsRepository(private val movieInterface: MovieInterface) {

    suspend fun getMovieDetails(imdbId: String): Results<MovieDetails> {
        return try{
            val response = movieInterface.getMovieDetails(imdbId, Constants.API_KEY)
            if(response.isSuccessful){
                Results(Status.SUCCESS, response.body(), null)
            }else{
                Results(Status.ERROR, null, null)
            }
        }catch (e: Exception){
            Results(Status.ERROR, null, null)
        }
    }
}