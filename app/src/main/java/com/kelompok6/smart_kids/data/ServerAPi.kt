package com.kelompok6.smart_kids.data

import com.kelompok6.smart_kids.data.request.LoginRequest
import com.kelompok6.smart_kids.data.request.RegisterRequest
import com.kelompok6.smart_kids.data.response.LoginResponse
import com.kelompok6.smart_kids.data.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerAPi {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("auth/register")
    suspend fun register( // ← NAMA FUNGSI HARUS register, BUKAN login!
        @Body request: RegisterRequest
    ): RegisterResponse



}