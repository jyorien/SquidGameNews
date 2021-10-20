package com.example.squidgamenews.data

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface SquidService {
    @GET("squidify")
    fun getSquidNews(@Header("x-rapidapi-host") host: String, @Header("x-rapidapi-key") key:String): Call<List<News>>

    companion object {
        private var INSTANCE: SquidService? = null
        fun getInstance(): SquidService {
            return INSTANCE ?: synchronized(this) {
                val client = OkHttpClient.Builder().build()
                val retrofit = Retrofit.Builder().client(client)
                    .baseUrl("https://squidify-squid-game-news.p.rapidapi.com/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                val service = retrofit.create(SquidService::class.java)
                INSTANCE = service
                service
            }
        }
    }
}