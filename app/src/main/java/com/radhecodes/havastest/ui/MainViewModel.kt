package com.radhecodes.havastest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.radhecodes.havastest.data.model.DateTimeResponse
import com.radhecodes.havastest.repository.HavasRepository

class MainViewModel(private val havasRepository: HavasRepository): ViewModel() {

    fun getDateTime(): LiveData<DateTimeResponse> {
        return havasRepository.getDateAndTime()
    }
}