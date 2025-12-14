package com.kelompok6.smart_kids.data.response

data class LoginResponse(
    val status: String?,       // "success" atau null
    val message: String?,
    val user: UserResponse?,   // opsional, sesuai kebutuhan
    val error: String? = null  // untuk menangkap error jika ada
)

data class UserResponse(
    val id: Int,
    val name: String,
    val email: String
)