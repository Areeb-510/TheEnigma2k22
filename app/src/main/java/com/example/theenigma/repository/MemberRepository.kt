package com.example.theenigma.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.theenigma.data.ExeTeamMembers
import com.example.theenigma.data.QuestionList
import com.example.theenigma.interfaces.MemberAPI

class MemberRepository(private val memberAPI: MemberAPI) {


    var membersLiveData = MutableLiveData<ExeTeamMembers>()

    val members : LiveData<ExeTeamMembers>
        get() = membersLiveData

    suspend fun getMembers(){

        val result = memberAPI.getMember()
        membersLiveData.value = result.body()
    }
}