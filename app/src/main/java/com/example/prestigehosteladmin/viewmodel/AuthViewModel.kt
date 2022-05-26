package com.example.prestigehosteladmin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.prestigehosteladmin.repository.AuthRepository
import com.example.prestigehosteladmin.utils.AuthState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    fun signUpUser(email: String, password: String): LiveData<AuthState> {
        return authRepository.signUpUser(email, password)
    }

    fun signInUser(email: String, password: String): LiveData<AuthState> {
        return authRepository.signInUser(email, password)
    }

    fun signOutUser(): LiveData<AuthState> {
        return authRepository.signOutUser()
    }

    fun getCurrentUser(): FirebaseUser? {
        return authRepository.getCurrentUser()
    }

}