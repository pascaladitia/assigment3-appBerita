package com.pascal.assigment3.network

import com.pascal.assigment3.model.ResponseServer
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {

    @GET("v2/everything?q=bitcoin&from=2020-07-14&sortBy=publishedAt&apiKey=d082efeab5b0475a9556e6a17b81371c")
    fun getDataNews() : Call<ResponseServer>
}