package com.example.theenigma.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theenigma.data.ExeTeamMembers
import com.example.theenigma.repository.MemberRepository
import kotlinx.coroutines.launch

class MembersViewModel(private val memberRepository: MemberRepository) : ViewModel() {


    init {
        viewModelScope.launch {
            memberRepository.getMembers()
        }
    }
    val allMembers : LiveData<ExeTeamMembers>
    get() = memberRepository.membersLiveData

}