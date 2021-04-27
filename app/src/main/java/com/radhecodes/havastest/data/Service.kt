package com.radhecodes.havastest.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {
    private const val HOST = "https://dateandtimeasjson.appspot.com"
    private const val TAG = "Service"

    val api: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Api::class.java)
    }
}