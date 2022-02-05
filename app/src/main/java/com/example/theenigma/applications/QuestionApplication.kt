package com.example.theenigma.applications

import android.app.Application
import com.example.theenigma.interfaces.RetrofitHelper
import com.example.theenigma.data.QuestionDataBase
import com.example.theenigma.interfaces.QuestionAPI
import com.example.theenigma.repository.QuestionRepository

class QuestionApplication : Application() {

    lateinit var questionRepository: QuestionRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize(){
        val questionAPI = RetrofitHelper.getInstance().create(QuestionAPI::class.java)
        val dataBase = QuestionDataBase.getDatabase(applicationContext)
        questionRepository = QuestionRepository(questionAPI,dataBase)
    }
}