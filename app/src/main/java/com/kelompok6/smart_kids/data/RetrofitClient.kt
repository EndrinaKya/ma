package com.kelompok6.smart_kids.data
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitClient {



    private const val BASE_URL = "http://192.168.1.51:3000/api/" // ganti ke URL asli

    val api: ServerAPi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerAPi::class.java)
    }
}