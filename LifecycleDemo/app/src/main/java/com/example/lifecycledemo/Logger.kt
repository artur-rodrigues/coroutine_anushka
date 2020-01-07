package com.example.lifecycledemo

import android.util.Log

fun <T> log(message: T) {
    if (message is String) {
        Log.i("TAG", message)
    } else {
        Log.i("TAG", message.toString())
    }
}