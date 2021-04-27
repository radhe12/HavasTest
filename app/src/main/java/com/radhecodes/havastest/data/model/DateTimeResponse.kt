package com.radhecodes.havastest.data.model

import com.google.gson.annotations.SerializedName

class DateTimeResponse(
    @SerializedName("datetime")
    val datetime: String
) {
    private var error: Throwable? = null

    constructor(error: Throwable?) : this("") {
        this.error = error
    }

    fun getErrors(): Throwable? {
        return error
    }
}