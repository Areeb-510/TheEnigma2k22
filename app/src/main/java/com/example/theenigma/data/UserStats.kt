package com.example.theenigma.data

data class UserStats(
    val username : String? = "",
    val imageUrl : String? = "",
    val score : Int? = 0,
    val questions_solved : Int? = 0
)