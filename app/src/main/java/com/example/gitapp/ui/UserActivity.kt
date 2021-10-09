package com.example.gitapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import com.example.gitapp.R
import com.example.gitapp.utils.showToast
import com.example.gitapp.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupObserver()

        findViewById<Button>(R.id.btnClick).setOnClickListener {
            userViewModel.runGetAllUsers()
        }

    }

    private fun setupObserver() {
        userViewModel.mUsers.observe(this, Observer {
            it?.let { resources ->
                when (resources.status) {
                    Status.SUCCESS -> {
                        this.showToast("Success")
                        for (i in it.data ?: emptyList()) {
                            Log.d("LOG_TAG", "onCreate: ${i.login}")
                        }
                    }
                    Status.ERROR -> {
                        this.showToast("Error")
                    }

                    Status.LOADING -> {
                        this.showToast("Loading")
                    }
                }
            }
        })
    }
}
