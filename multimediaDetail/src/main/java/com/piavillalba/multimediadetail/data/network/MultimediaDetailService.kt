package com.piavillalba.multimediadetail.data.network

import com.piavillalba.multimediadetail.data.entities.MovieResponse
import com.piavillalba.multimediadetail.data.entities.TvshowResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MultimediaDetailService {

    @GET(GET_MOVIE_BY_ID)
    suspend fun getMovieById(@Path(value = "id") id: Int): MovieResponse

    @GET(GET_TVSHOW_BY_ID)
    suspend fun getTvshowById(@Path(value = "id") id: Int): TvshowResponse
}

private const val GET_MOVIE_BY_ID = "movie/{id}"
private const val GET_TVSHOW_BY_ID = "tv/{id}"
