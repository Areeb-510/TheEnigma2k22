package com.example.theenigma.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.theenigma.data.QuestionDataBase
import com.example.theenigma.data.QuestionList
import com.example.theenigma.interfaces.QuestionAPI

class QuestionRepository(private val questionAPI: QuestionAPI,private val questionDataBase: QuestionDataBase) {

    private var questionLiveData = MutableLiveData<QuestionList>()

    val questions : LiveData<QuestionList>
    get() = questionLiveData

    suspend fun getQuestions(){

        val result = questionAPI.getQuestion()

        if(result.body()!=null ){
            questionDataBase.getQuestionDao().addQuestion(result.body()!!)
            questionLiveData.postValue(result.body())
        }
    }
}