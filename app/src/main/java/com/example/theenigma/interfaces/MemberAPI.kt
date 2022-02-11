package com.example.theenigma.interfaces

import com.example.theenigma.data.ExeTeamMembers
import retrofit2.Response
import retrofit2.http.GET

interface MemberAPI {

    @GET("/get_members")
    suspend fun getMember() : Response<ExeTeamMembers>
}