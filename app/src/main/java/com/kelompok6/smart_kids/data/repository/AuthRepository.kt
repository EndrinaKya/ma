package com.kelompok6.smart_kids.data.repository

import com.kelompok6.smart_kids.data.RetrofitClient
import com.kelompok6.smart_kids.data.ServerAPi
import com.kelompok6.smart_kids.data.request.LoginRequest
import com.kelompok6.smart_kids.data.request.RegisterRequest
import com.kelompok6.smart_kids.data.response.LoginResponse
import com.kelompok6.smart_kids.data.response.RegisterResponse

class AuthRepository(
    private val api: ServerAPi = RetrofitClient.api
) {
    /**
     * Melakukan proses login ke server
     * @return [LoginResponse] dari API
     */
    suspend fun login(email: String, password: String): LoginResponse {
        val request = LoginRequest(email, password)
        return api.login(request)
    }

    /**
     * Melakukan proses registrasi (register) ke server
     * @return [RegisterResponse] dari API
     */
    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        val request = RegisterRequest(name, email, password)
        return api.register(request)
    }
}