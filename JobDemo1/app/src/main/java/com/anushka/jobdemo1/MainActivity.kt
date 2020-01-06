package com.anushka.jobdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    lateinit var job1: Job
    lateinit var deferred1: Deferred<Int>
    var returnedValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Funcionamento com Job
         */
        /*job1 = CoroutineScope(Main).launch {
            downloadData()
        }

        cancelButton.setOnClickListener {
            job1.cancel()
        }

        statusButton.setOnClickListener {
            when {
                job1.isActive -> textView.text = "Active"
                job1.isCancelled -> textView.text = "Cancelled"
                job1.isCompleted -> textView.text = "Completed"
            }
        }*/

        /**
         * Funcionamento com Deferred
         */
        job1 = CoroutineScope(Main).launch {
//            downloadData()
            deferred1 = async {
                downloadData2()
            }
            returnedValue = deferred1.await()
            log("*** $returnedValue ***")
        }

        cancelButton.setOnClickListener {
            deferred1.cancel()
        }

        statusButton.setOnClickListener {
            when {
                deferred1.isActive -> textView.text = "Active"
                deferred1.isCancelled -> textView.text = "Cancelled"
                deferred1.isCompleted -> textView.text = "Completed"
            }
        }
    }

    private suspend fun downloadData() {
        withContext(IO) {
            repeat(30) {
                delay(1000)
                log("repeating $it")
            }
        }
    }

    private suspend fun downloadData2(): Int {
        var i = 0
        withContext(IO) {
            repeat(30) {
                delay(1000)
                log("repeating $it")
                i++
            }
        }

        return i
    }

    private fun log(message: String) {
        Log.i("TAG", message)
    }
}