package com.example.prestigehosteladmin.repository

import androidx.lifecycle.MutableLiveData
import com.example.prestigehosteladmin.utils.AuthState
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    fun signUpUser(email: String, password: String): MutableLiveData<AuthState> {
        val userMutableLiveData = MutableLiveData<AuthState>()
        val authState = AuthState()
        authState.isLoading = true
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                authState.isLoading = false
                authState.isSuccess = true
                authState.message = "SignUp completed"
            } else {
                authState.isLoading = false
                authState.exception = task.exception
            }
            userMutableLiveData.value = authState
        }

        return userMutableLiveData
    }

    fun signInUser(email: String, password: String): MutableLiveData<AuthState> {
        val userMutableLiveData = MutableLiveData<AuthState>()
        val authState = AuthState()
        authState.isLoading = true
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    authState.isLoading = false
                    authState.isSuccess = true
                    authState.message = "SignIn successful"
                } else {
                    authState.isLoading = false
                    authState.exception = task.exception
                }
                userMutableLiveData.value = authState
            }
        return userMutableLiveData
    }

    fun signOutUser(): MutableLiveData<AuthState> {

        val userMutableLiveData = MutableLiveData<AuthState>()
        val authState = AuthState()
        authState.isLoading = true

        try {
            auth.signOut()
            authState.isLoading = false
            authState.isSuccess = true
            authState.message = "SignOut successful"


        } catch (exception: FirebaseException) {

            authState.isLoading = false
            authState.message=exception.localizedMessage
        }
        return userMutableLiveData
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}