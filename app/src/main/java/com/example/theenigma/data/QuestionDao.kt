package com.example.theenigma.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface QuestionDao {
    @Insert(onConflict  =OnConflictStrategy.ABORT)
    suspend fun addQuestion(questions : List<QuestionListItem>)


    @Query("SELECT * FROM questions")
    suspend fun getAllQuestions() : List<QuestionListItem>


    @Query("SELECT description from questions WHERE question_number == :ques_no")
    suspend fun getSecificQuestion(ques_no : Int) : String
}