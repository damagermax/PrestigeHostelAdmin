package com.example.prestigehosteladmin.utils

import android.content.Context
import android.widget.Toast
import com.shencoder.loadingdialog.LoadingDialog

fun Context.toastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}



