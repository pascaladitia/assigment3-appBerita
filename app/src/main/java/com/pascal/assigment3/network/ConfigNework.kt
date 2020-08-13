package com.pascal.assigment3.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNework {

    companion object {
        fun getRetrofit() : NewsService {

            val retrofit = Retrofit.Builder()
                .baseUrl("http://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(NewsService::class.java)
            return service
        }
    }
}