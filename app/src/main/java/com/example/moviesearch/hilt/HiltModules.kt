package com.example.moviesearch.hilt

import com.example.moviesearch.remote.MovieInterface
import com.example.moviesearch.ui.details.MovieDetailsRepository
import com.example.moviesearch.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Provides
    fun provideRetrofitInterface(): MovieInterface{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(MovieInterface::class.java)

    }

    @Provides
    fun provideRepository(movieInterface: MovieInterface): MovieDetailsRepository{
        return MovieDetailsRepository(movieInterface)
    }


}