package com.pascal.assigment3

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.pascal.assigment3.adapter.NewsAdapter
import com.pascal.assigment3.model.News
import com.pascal.assigment3.model.ResponseServer
import com.pascal.assigment3.network.ConfigNework
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parsingApi()
    }

    private fun parsingApi() {

        if (isConnect()) {

            ConfigNework.getRetrofit().getDataNews().enqueue(object : Callback<ResponseServer> {
                override fun onFailure(call: Call<ResponseServer>, t: Throwable) {

                    progress_main.visibility = View.GONE
                    Log.d("server error", t.message)
                }

                override fun onResponse(
                    call: Call<ResponseServer>,
                    response: Response<ResponseServer>
                ) {
                    progress_main.visibility = View.GONE
                    Log.d("server connect", response.message())

                    if (response.isSuccessful) {
                        val status = response.body()?.status

                        if(status == "ok") {
                            val data = response.body()?.articles

                            showData(data)
                        }
                    }
                }

            })
        } else {
            progress_main.visibility = View.GONE
            Toast.makeText(this, "device no internet", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun isConnect() : Boolean {
        val connect : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo.isConnected
    }

    private fun showData(data: ArrayList<News>?) {
        recyclerview_main.adapter = NewsAdapter(data)
        recyclerview_main.setHasFixedSize(true)
    }
}