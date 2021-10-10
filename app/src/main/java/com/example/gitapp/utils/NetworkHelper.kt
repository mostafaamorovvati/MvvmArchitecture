package com.example.gitapp.utils

import com.blankj.utilcode.util.NetworkUtils

class NetworkHelper {

    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isConnected()
    }
}