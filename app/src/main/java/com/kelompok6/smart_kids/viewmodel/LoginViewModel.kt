package com.kelompok6.smart_kids.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelompok6.smart_kids.data.repository.AuthRepository // ← tambahkan ini
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    // Gunakan repository, bukan API langsung
    private val authRepository = AuthRepository()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            try {
                // ✅ Panggil repository
                val response = authRepository.login(email, password)

                if (response.success) {
                    _loginState.value = LoginState.Success(response.token ?: "")
                } else {
                    _loginState.value = LoginState.Error(response.message)
                }

            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Terjadi kesalahan")
            }
        }
    }
}

// Tetap di file yang sama (atau pisahkan jika mau)
sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}