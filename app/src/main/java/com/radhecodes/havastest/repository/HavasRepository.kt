package com.radhecodes.havastest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.radhecodes.havastest.data.Service
import com.radhecodes.havastest.data.model.DateTimeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface HavasRepository {
    fun getDateAndTime(): LiveData<DateTimeResponse>
}

class HavasRepositoryImpl: HavasRepository {
    lateinit var dateTimeResponse: Call<DateTimeResponse>
    private var dateTime: MutableLiveData<DateTimeResponse> = MutableLiveData()
    private val api = Service.api
    override fun getDateAndTime(): LiveData<DateTimeResponse> {
        dateTimeResponse = api.getDateTime()

        dateTimeResponse.enqueue(object : Callback<DateTimeResponse> {
            override fun onResponse(
                call: Call<DateTimeResponse>,
                response: Response<DateTimeResponse>
            ) {
                if(response.isSuccessful) {
                    dateTime.value = response.body()
                }
            }

            override fun onFailure(call: Call<DateTimeResponse>, t: Throwable) {
                dateTime.value = DateTimeResponse(t)
            }

        })
        return dateTime
    }

}