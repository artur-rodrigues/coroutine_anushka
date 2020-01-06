package com.anushka.timeoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            CoroutineScope(Dispatchers.Main).launch {
                withTimeout(5000) {
                    withContext(IO) {
                        for(i in 1..200000) {

                            delay(1000)
                            Log.i("TAG", "round $i")
                        }
                    }
                }
            }
        }
}
