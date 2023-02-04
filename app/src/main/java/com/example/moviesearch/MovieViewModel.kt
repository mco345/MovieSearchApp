package com.example.moviesearch

import android.provider.CalendarContract
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.moviesearch.data.moviedetails.MovieDetails
import com.example.moviesearch.remote.MovieInterface
import com.example.moviesearch.ui.details.MovieDetailsRepository
import com.example.moviesearch.ui.movie.MoviePaging
import com.example.moviesearch.utils.Event
import com.example.moviesearch.utils.Results
import com.example.moviesearch.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieInterface: MovieInterface, private val repository: MovieDetailsRepository): ViewModel() {

    private val query = MutableLiveData<String>()

    val list = query.switchMap {query ->
        Pager(PagingConfig(pageSize = 10)){
            MoviePaging(query, movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String){
        query.postValue(s)
    }

    private val _movieDetails = MutableLiveData<Event<Results<MovieDetails>>>()
    val movieDetails: LiveData<Event<Results<MovieDetails>>> = _movieDetails


    fun getMovieDetails(imdbId: String) = viewModelScope.launch {

        _movieDetails.postValue(Event(Results(Status.LOADING, null, null)))
        _movieDetails.postValue(Event(repository.getMovieDetails(imdbId)))

    }
}