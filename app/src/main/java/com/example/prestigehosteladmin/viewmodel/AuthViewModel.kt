package com.example.prestigehosteladmin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


const val TAG = "MAXWELL"

class AuthViewModel : ViewModel() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()





    suspend fun signUp(email: String, password: String): FirebaseUser? {


        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Log.d(TAG, "current user   $user")





                } else {
                    // If sign in fails, display a message to the user.



                    Log.d(TAG, "createUserWithEmail:failure", task.exception)

                    task.exception?.let {
                        Log.d(TAG, "Email signup failed with error ${it.localizedMessage}")


                    }


                }
            }

        return auth.currentUser
    }


    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser


                } else {
                    // If sign in fails, display a message to the user.
                    Log.d(TAG, "signInWithEmail:failure", task.exception)


                }
            }


    }

    fun checkIfUserIsSignIn() {
        val currentUser = auth.currentUser
        if (currentUser != null) {

        }
    }

}