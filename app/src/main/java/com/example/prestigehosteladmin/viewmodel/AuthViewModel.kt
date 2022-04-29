package com.example.prestigehosteladmin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prestigehosteladmin.utils.AuthState
import com.google.firebase.auth.FirebaseAuth


const val TAG="MAXWELL"
class AuthViewModel : ViewModel() {

    private  var auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState by lazy { MutableLiveData<AuthState>(AuthState.Idle) }
    val authState: LiveData<AuthState> = _authState





    fun signUp(email:String,password:String){
        auth.createUserWithEmailAndPassword( email , password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Log.d(TAG, "current user   $user")

                    _authState.value=AuthState.Success

                } else {
                    // If sign in fails, display a message to the user.
                    Log.d(TAG, "createUserWithEmail:failure", task.exception)

                    task.exception?.let {
                        Log.d(TAG,"Email signup failed with error ${it.localizedMessage}")
                        _authState.value = AuthState.AuthError(it.localizedMessage)
                    }
                }
            }
    }



fun signIn(email:String,password:String){
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

}