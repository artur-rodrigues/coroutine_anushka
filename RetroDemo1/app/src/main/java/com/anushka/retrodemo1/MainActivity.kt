package com.anushka.retrodemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anushka.retrodemo1.retrofit.Album
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        getAlbum()
    }

    private fun getAlbum() {
        viewModel.album.observe(this, Observer {

            if (it is Result.Success<*>) {
                textView.text = (it.success as Album).title
            } else if(it is Result.Error) {
                textView.text = it.exception
            }
        })
    }
}
