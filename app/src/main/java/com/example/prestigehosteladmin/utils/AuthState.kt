package com.example.prestigehosteladmin.utils

import java.lang.Exception

data class AuthState(
    var isLoading: Boolean = false,
    var isSuccess: Boolean = false,
    var message: String? = null,
    var exception: Exception? = null
)