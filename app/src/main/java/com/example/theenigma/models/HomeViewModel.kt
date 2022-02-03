package com.example.theenigma.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theenigma.data.UserStats
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class HomeViewModel(var progress : Int) : ViewModel() {

    var prog = MutableLiveData<Int>(progress)




    fun updateProgress(){
        prog.value = progress
    }


}