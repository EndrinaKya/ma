package com.kelompok6.smart_kids.data.response

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val token: String?
)