package com.anushka.asyncawaitdemo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main


class MainActivity : AppCompatActivity() {

    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Execução sequêncial de coroutine
        /*job = CoroutineScope(IO).launch {
            log("Calculation started")

            val stock1 = getStock1()
            val stock2 = getStock2()
            val total = stock1 + stock2

            log("Total is $total")
        }*/

        // Execução em paralelo de coroutine
        job = CoroutineScope(Main).launch {
            log("Calculation started")

            val stock1 = async(IO, CoroutineStart.LAZY) {
                getStock1()
            }

            val stock2 = async(IO, CoroutineStart.LAZY) {
                getStock2()
            }

            delay(4000)
            log("*************Just before await*****************")

            val total = stock1.await() + stock2.await()

            val info = "Total is $total"
            log(info)
            Toast.makeText(applicationContext, info, Toast.LENGTH_LONG).show()
        }
    }

    private suspend fun getStock1(): Int {
        return getStock(10, 1, 55000)
    }

    private suspend fun getStock2(): Int {
        return getStock(8, 2, 35000)
    }

    private suspend fun getStock(delay: Long, stockNumber: Int, result: Int): Int {
        log("counting stock $stockNumber started")
        delay(delay * 1000)
        log("stock $stockNumber returned")
        return result
    }

    private fun log(message: String) {
        Log.d("MY_TAG", message)
    }

    override fun onDestroy() {
        super.onDestroy()

        if (::job.isInitialized) {
            job.cancel()
        }
    }
}