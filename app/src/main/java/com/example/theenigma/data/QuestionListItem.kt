package com.example.theenigma.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "questions")
data class QuestionListItem(
    @PrimaryKey(autoGenerate = true)
    val questionID : Int,
    val description: String,
    val gltf_model_name: String,
    val question_name: String,
    val question_number: Int
)