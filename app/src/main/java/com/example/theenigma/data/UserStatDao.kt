package com.example.theenigma.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserStatDao {

    val db = FirebaseFirestore.getInstance()

    val leaderboardCollection: CollectionReference
        get() = db.collection("Leaderboards")

    fun addUser(userstats : UserStats){

        GlobalScope.launch(Dispatchers.IO) {

            userstats.username?.let { leaderboardCollection.document(it).set(userstats) }

        }
    }
}