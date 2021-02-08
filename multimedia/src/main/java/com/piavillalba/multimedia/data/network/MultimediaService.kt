package com.piavillalba.multimedia.data.network

import com.piavillalba.multimedia.data.entities.MoviesResponse
import com.piavillalba.multimedia.data.entities.TvshowsResponse
import retrofit2.http.GET

interface MultimediaService {

    @GET(GET_MOVIES)
    suspend fun getPopularMovies(): MoviesResponse

    @GET(GET_TVSHOWS)
    suspend fun getPopularTvshows(): TvshowsResponse
}

private const val GET_MOVIES = "movie/popular"
private const val GET_TVSHOWS = "tv/popular"
