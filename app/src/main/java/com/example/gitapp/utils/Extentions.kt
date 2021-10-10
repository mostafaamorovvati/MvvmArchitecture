package com.example.gitapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}