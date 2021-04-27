package com.radhecodes.havastest.data

import com.radhecodes.havastest.data.model.DateTimeResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET(".")
    fun getDateTime(): Call<DateTimeResponse>
}