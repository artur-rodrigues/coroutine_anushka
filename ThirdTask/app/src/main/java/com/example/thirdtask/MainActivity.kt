package com.example.thirdtask

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Isto foi só uma brincadeira, o mesmo resultado pode ser atingido com launch
        /*btnProcessar.setOnClickListener {
            stringResult.setText(R.string.string_result)
            intResult.setText(R.string.int_result)
            booleanResult.setText(R.string.boolean_result)

            executeWorkIndependent(stringProgress, stringResult, 5000, "Teste")
            executeWorkIndependent(intProgress, intResult, 10000, 231)
            executeWorkIndependent(booleanProgress, booleanResult, 15000, false)
        }*/

        /**
         * Mostra concorrência, onde são disparados três processos asyncronos
         * e no momento que todos os processos finalizam o resultado é mostrado
         * na tela
         */
        btnProcessar.setOnClickListener {
            stringResult.setText(R.string.string_result)
            intResult.setText(R.string.int_result)
            booleanResult.setText(R.string.boolean_result)

            CoroutineScope(Main).launch {
                setVisibility(false, stringResult, intResult, booleanResult)
                setVisibility( true, stringProgress, intProgress, booleanProgress)

                val resultadoString = async {
                    delay(5000)
                    "Teste"
                }

                val resultadoInt = async {
                    delay(10000)
                    1234
                }

                val resultadoBoolean = async {
                    delay(15000)
                    false
                }

                val resultadoFinal = "${resultadoString.await()} ${resultadoInt.await()} ${resultadoBoolean.await()}"

                setVisibility( false, stringProgress, intProgress, booleanProgress)
                setVisibility(true, stringResult, intResult, booleanResult)

                Toast.makeText(applicationContext, resultadoFinal, Toast.LENGTH_LONG).show()

                stringResult.text = resultadoFinal
                intResult.text = resultadoFinal
                booleanResult.text = resultadoFinal
            }
        }
    }

    // Isto foi só uma brincadeira, o mesmo resultado pode ser atingido com launch
    private fun <T> executeWorkIndependent(progress: ProgressBar,
                                           textView: TextView,
                                           time: Long,
                                           resultProvided: T) {

        CoroutineScope(Main).launch {
            setVisibility(false, textView)
            setVisibility(true, progress)

            val result = async(IO) {
                delay(time)
                resultProvided
            }

            setText(textView, result.await())

            setVisibility(false, progress)
            setVisibility(true, textView)
        }
    }

    private fun setVisibility(visible: Boolean, vararg view: View) {
        for (vi in view) {
            if (visible) {
                vi.visibility = View.VISIBLE
            } else {
                vi.visibility = View.INVISIBLE
            }

        }
    }


    private fun <T> setText(label: TextView, result: T) {
        label.text = label.text.toString() + result
    }
}
