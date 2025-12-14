package com.kelompok6.smart_kids.data

import com.kelompok6.smart_kids.data.request.LoginRequest
import com.kelompok6.smart_kids.data.request.RegisterRequest

import com.kelompok6.smart_kids.data.response.LoginResponse
import com.kelompok6.smart_kids.data.response.RegisterResponse

import com.kelompok6.smart_kids.data.response.AlphabetListResponse
import com.kelompok6.smart_kids.data.response.AlphabetItem
import com.kelompok6.smart_kids.data.response.AlphabetResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServerAPi {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse

    // ✅ TAMBAHKAN INI
    @GET("alphabet")
    suspend fun getAlphabetList(): Response<AlphabetListResponse>

    @GET("alphabet/{letter}")
    suspend fun getAlphabetSound(@Path("letter") letter: String): Response<AlphabetResponse>
}