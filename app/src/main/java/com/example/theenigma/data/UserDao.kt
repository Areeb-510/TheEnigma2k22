package com.example.theenigma.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {

    val db: FirebaseFirestore
        get() = FirebaseFirestore.getInstance()

    val userCollection: CollectionReference
        get() = db.collection("users")

    fun addUser(user : User){

        GlobalScope.launch(Dispatchers.IO) {

            user.displayName?.let { userCollection.document(it).set(user) }

        }
    }


}