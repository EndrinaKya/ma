package com.kelompok6.smart_kids.data.response

// Respons untuk: GET /api/alphabet → daftar semua huruf
data class AlphabetListResponse(
    val alphabets: List<AlphabetItem>
)

// Respons untuk: GET /api/alphabet/A → satu huruf
data class AlphabetResponse(
    val letter: String,
    val audio_url: String
)

// Item data (bisa dipakai di daftar atau konversi)
data class AlphabetItem(
    val letter: String,
    val audio_url: String
)