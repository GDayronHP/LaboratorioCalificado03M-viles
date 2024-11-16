package com.huayra.joseph.laboratoriocalificado3.data

import com.huayra.joseph.laboratoriocalificado3.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofitInstance.create(ApiService::class.java)
    }
}
