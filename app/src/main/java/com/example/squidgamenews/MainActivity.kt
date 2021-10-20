package com.example.squidgamenews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.squidgamenews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.newsList.adapter = NewsAdapter()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.news.observe(this) {
            (binding.newsList.adapter as NewsAdapter).submitList(it)
        }
    }
}