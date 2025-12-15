package com.kelompok6.smart_kids.data.response

data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val token: String? = null
)