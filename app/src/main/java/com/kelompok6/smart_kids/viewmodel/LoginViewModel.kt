package com.kelompok6.smart_kids.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelompok6.smart_kids.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            try {
                val response = authRepository.login(email, password)

                // ✅ Cek berdasarkan "status", bukan "success"
                if (response.status == "success") {
                    _loginState.value = LoginState.Success
                } else {
                    _loginState.value = LoginState.Error(
                        response.message ?: "Login gagal"
                    )
                }

            } catch (e: Exception) {
                // Log error untuk debug
                e.printStackTrace()
                _loginState.value = LoginState.Error(
                    e.message ?: "Kesalahan saat login"
                )
            }
        }
    }

    fun resetState() {
        _loginState.value = LoginState.Idle
    }
}

/* =========================
   LOGIN STATE
   ========================= */
sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
