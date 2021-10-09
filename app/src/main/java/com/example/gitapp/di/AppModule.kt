package com.example.gitapp.di

import com.example.gitapp.data.remote.api.ApiHelper
import com.example.gitapp.data.remote.api.ApiHelperImpl
import com.example.gitapp.utils.NetworkHelper
import org.koin.dsl.module

val appModule = module {

    single<ApiHelper> { return@single ApiHelperImpl(get()) }
    factory { provideNetworkHelper() }

}

private fun provideNetworkHelper() = NetworkHelper()
