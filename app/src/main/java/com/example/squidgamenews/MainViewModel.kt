package com.example.squidgamenews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.squidgamenews.data.News
import com.example.squidgamenews.data.SquidService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val host = ""
const val key = ""
class MainViewModel: ViewModel() {
    private val squidService = SquidService.getInstance()

    private val _news = MutableLiveData<List<News>>()
    val news : LiveData<List<News>>
    get() = _news

    init {
        getNews()
    }

    private fun getNews() {
        squidService.getSquidNews(host, key).enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                val body = response.body()

                _news.value = body
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                Log.d("hello","Failed to fetch ${t.localizedMessage}")
            }

        })
    }
}