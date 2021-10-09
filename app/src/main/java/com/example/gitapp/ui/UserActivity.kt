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

    private val mUserViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupObserver()

        findViewById<Button>(R.id.btnClick).setOnClickListener {
            mUserViewModel.runGetAllUsers()
        }

    }

    private fun setupObserver() {
        mUserViewModel.mUsers.observe(this, Observer {
            it?.let { resources ->
                when (resources.status) {
                    Status.SUCCESS -> {
                        this.showToast("Success")

                        for (i in it.data ?: emptyList()) {
                            Log.d("LOG_TAG", "onCreate: ${i.author}")
                        }

                    }
                    Status.ERROR -> {
                        this.showToast(it.message.toString())
                    }

                    Status.LOADING -> {
                        this.showToast("Loading")
                    }
                }
            }
        })
    }
}
