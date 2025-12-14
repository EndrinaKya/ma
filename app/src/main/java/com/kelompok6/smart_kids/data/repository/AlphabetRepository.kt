package com.kelompok6.smart_kids.data.repository

import com.kelompok6.smart_kids.data.ServerAPi
import com.kelompok6.smart_kids.data.response.AlphabetListResponse
import com.kelompok6.smart_kids.data.response.AlphabetResponse
import retrofit2.Response

class AlphabetRepository(private val api: ServerAPi) {

    suspend fun getAlphabetList(): Response<AlphabetListResponse> {
        return api.getAlphabetList()
    }

    suspend fun getAlphabetSound(letter: String): Response<AlphabetResponse> {
        return api.getAlphabetSound(letter)
    }
}