package com.example.gitapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.gitapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity : AppCompatActivity() {

    private val userViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel.data.observe(this, Observer {
            // Populate the UI

            for (i in it.withIndex()) {
                Log.d("LOG_TAG", "onCreate: ${it[i.index].login}")
            }
        })

        userViewModel.loadingState.observe(this, Observer {
            // Observe the loading state
        })
    }
}
