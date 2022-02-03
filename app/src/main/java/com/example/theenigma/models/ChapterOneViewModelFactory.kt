package com.example.theenigma.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.theenigma.repository.QuestionRepository

class ChapterOneViewModelFactory(val repository: QuestionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChapterOneViewModel(repository) as T
    }
}