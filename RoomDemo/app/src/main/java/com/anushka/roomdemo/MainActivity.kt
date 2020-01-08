package com.anushka.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anushka.roomdemo.db.Subscriber
import com.anushka.roomdemo.db.SubscriberDatabase
import com.anushka.roomdemo.db.SubscriberRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(applicationContext).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)

        displaySubscribersList()

        save_button.setOnClickListener {
            viewModel.insert(Subscriber(0,
                name_text.text.toString(),
                email_text.text.toString()))
        }

        clear_button.setOnClickListener {
            viewModel.clearAll()
        }
    }

    private fun displaySubscribersList() {
        viewModel.subscribers.observe(this, Observer {
            subscribers_textview.text = it.toString()
        })
    }
}
