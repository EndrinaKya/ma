package com.kelompok6.smart_kids.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelompok6.smart_kids.data.repository.AlphabetRepository
import com.kelompok6.smart_kids.data.RetrofitClient
import com.kelompok6.smart_kids.data.response.AlphabetItem
import com.kelompok6.smart_kids.data.response.AlphabetResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlphabetViewModel : ViewModel() {

    private val repository = AlphabetRepository(RetrofitClient.api)

    // State untuk daftar huruf (dari /api/alphabet)
    private val _alphabetListState = MutableStateFlow<AlphabetListState>(AlphabetListState.Idle)
    val alphabetListState: StateFlow<AlphabetListState> = _alphabetListState

    // State untuk suara satu huruf (dari /api/alphabet/{letter})
    private val _alphabetSoundState = MutableStateFlow<AlphabetSoundState>(AlphabetSoundState.Idle)
    val alphabetSoundState: StateFlow<AlphabetSoundState> = _alphabetSoundState

    // Ambil semua huruf dari API
    fun fetchAlphabetList() {
        _alphabetListState.value = AlphabetListState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getAlphabetList()
                if (response.isSuccessful && response.body() != null) {
                    _alphabetListState.value = AlphabetListState.Success(response.body()!!.alphabets)
                } else {
                    _alphabetListState.value = AlphabetListState.Error("Gagal: ${response.code()}")
                }
            } catch (e: Exception) {
                _alphabetListState.value = AlphabetListState.Error("Kesalahan jaringan: ${e.message}")
            }
        }
    }

    // Ambil URL audio untuk satu huruf
    fun fetchSoundUrl(letter: String) {
        _alphabetSoundState.value = AlphabetSoundState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getAlphabetSound(letter.uppercase())
                if (response.isSuccessful && response.body() != null) {
                    val audioUrl = response.body()!!.audio_url
                    _alphabetSoundState.value = AlphabetSoundState.Success(audioUrl)
                } else {
                    val msg = when (response.code()) {
                        404 -> "Huruf tidak ditemukan"
                        400 -> "Huruf tidak valid"
                        else -> "Gagal memuat suara (${response.code()})"
                    }
                    _alphabetSoundState.value = AlphabetSoundState.Error(msg)
                }
            } catch (e: Exception) {
                _alphabetSoundState.value = AlphabetSoundState.Error("Kesalahan: ${e.message}")
            }
        }
    }

    fun resetSoundState() {
        _alphabetSoundState.value = AlphabetSoundState.Idle
    }
}

// State untuk daftar huruf
sealed class AlphabetListState {
    data object Idle : AlphabetListState()
    data object Loading : AlphabetListState()
    data class Success(val list: List<AlphabetItem>) : AlphabetListState()
    data class Error(val message: String) : AlphabetListState()
}

// State untuk suara
sealed class AlphabetSoundState {
    data object Idle : AlphabetSoundState()
    data object Loading : AlphabetSoundState()
    data class Success(val audioUrl: String) : AlphabetSoundState()
    data class Error(val message: String) : AlphabetSoundState()
}