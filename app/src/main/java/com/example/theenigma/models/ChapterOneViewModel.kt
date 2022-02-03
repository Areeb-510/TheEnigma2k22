package com.example.theenigma.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theenigma.data.QuestionList
import com.example.theenigma.repository.QuestionRepository
import kotlinx.coroutines.launch

class ChapterOneViewModel(private val repository: QuestionRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.getQuestions()
        }
    }

    val questions : LiveData<QuestionList>
        get() = repository.questions


}