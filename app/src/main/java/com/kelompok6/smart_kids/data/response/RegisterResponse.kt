package com.kelompok6.smart_kids.data.response

data class RegisterResponse(
    val status: String?,    // "success" atau null
    val message: String?,
    val error: String? = null  // opsional, untuk konsistensi penanganan error
)