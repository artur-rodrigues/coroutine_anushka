package com.anushka.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main).launch {
            log("Coroutine luanched from ${Thread.currentThread().name}")
            DemoClass().test1()
            log("Came back to Main Activity ${Thread.currentThread().name}")
        }
    }

    private fun log(message: String) {
        Log.i("MyTag",message)
    }
}
