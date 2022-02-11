package com.example.theenigma.interfaces

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MemberAPIRetrofitHelper {

    val BASE_URL = "https://d60c-2405-201-600e-301e-8062-d83-898f-c041.ngrok.io/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}