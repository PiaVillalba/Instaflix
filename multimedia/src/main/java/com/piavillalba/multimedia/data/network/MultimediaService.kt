package com.piavillalba.multimedia.data.network

import com.piavillalba.multimedia.data.entities.GenresResponse
import com.piavillalba.multimedia.data.entities.MoviesResponse
import com.piavillalba.multimedia.data.entities.TvshowsResponse
import retrofit2.http.GET

interface MultimediaService {

    @GET(GET_MOVIES)
    suspend fun getPopularMovies(): MoviesResponse

    @GET(GET_TVSHOWS)
    suspend fun getPopularTvshows(): TvshowsResponse

    @GET(GET_GENRE_MOVIES)
    suspend fun getMovieGenres(): GenresResponse

    @GET(GET_GENRE_TVSHOWS)
    suspend fun getTvGenres(): GenresResponse
}

private const val GET_MOVIES = "movie/popular"
private const val GET_TVSHOWS = "tv/popular"
private const val GET_GENRE_MOVIES = "genre/movie/list"
private const val GET_GENRE_TVSHOWS = "genre/tv/list"
