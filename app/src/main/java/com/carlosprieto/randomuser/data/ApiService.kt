package com.carlosprieto.randomuser.data

import com.carlosprieto.randomuser.data.dto.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api")
    suspend fun getUsers(@Query("results") numberResult: Int): Response<Results>
}