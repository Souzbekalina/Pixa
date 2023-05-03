package com.alina.pixa

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
     fun getImages(
        @Query("per_page") perPage: Int=3,
        @Query("key") key: String = "35831793-5d2dc70694943d02d429f482c",
        @Query("q") search: String,
        @Query("page") page: Int,
    ):Call<PixaModel>




}