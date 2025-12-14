package com.kelompok6.smart_kids.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelompok6.smart_kids.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    public val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun register(namaLengkap: String, email: String, password: String) {
        _registerState.value = RegisterState.Loading

        viewModelScope.launch {
            try {
                val response = authRepository.register(namaLengkap, email, password)

                if (response.success) {
                    _registerState.value = RegisterState.Success
                } else {
                    _registerState.value = RegisterState.Error(response.message)
                }

            } catch (e: Exception) {
                _registerState.value = RegisterState.Error(e.message ?: "Terjadi kesalahan jaringan")
            }
        }
    }
}

sealed class RegisterState {
    data object Idle : RegisterState()
    data object Loading : RegisterState()
    data object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}