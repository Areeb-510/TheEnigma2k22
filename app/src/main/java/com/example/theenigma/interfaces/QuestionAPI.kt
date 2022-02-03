package com.example.theenigma.interfaces

import com.example.theenigma.data.QuestionList
import retrofit2.Response
import retrofit2.http.GET

interface QuestionAPI {

    @GET("/get_question")
    suspend fun getQuestion() : Response<QuestionList>
}