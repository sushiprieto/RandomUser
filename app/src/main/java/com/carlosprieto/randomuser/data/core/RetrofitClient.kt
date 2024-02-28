package com.carlosprieto.randomuser.data.core

import com.carlosprieto.randomuser.data.ApiService
import com.carlosprieto.randomuser.view.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val initRetrofit: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}