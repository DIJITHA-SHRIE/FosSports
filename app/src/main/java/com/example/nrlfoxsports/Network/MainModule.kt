package com.example.nrlfoxsports.Network

import com.example.nrlfoxsports.Repository.DataRepository
import com.example.nrlfoxsports.ViewModel.StatsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val mainModule = module {

        single { DataRepository(get()) }

        single { createWebService() }

        viewModel { StatsViewModel(get()) }

    }

fun createWebService(): NetworkApi {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://statsapi.foxsports.com.au")
        .build()

    return retrofit.create(NetworkApi::class.java)
}



