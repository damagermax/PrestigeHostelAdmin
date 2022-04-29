package com.example.prestigehosteladmin.utils

sealed class AuthState{
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    class AuthError(val message: String? = null) : AuthState()
}
