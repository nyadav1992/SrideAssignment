package com.example.srideassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.srideassignment.adapter.NewsAdapter
import com.example.srideassignment.api.Response
import com.example.srideassignment.databinding.MainActivityBinding
import com.example.srideassignment.utils.NetworkUtils
import com.example.srideassignment.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var activityMainBinding: MainActivityBinding
    private lateinit var newsAdapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        if (NetworkUtils.isInternetAvailable(this))
            getNewsData()
        else
            showError(getString(R.string.no_network))

    }

    private fun showError(msg: String) {
        activityMainBinding.shimmerLayout.visibility = View.GONE
        activityMainBinding.rvNews.visibility = View.GONE
        activityMainBinding.tvError.visibility = View.VISIBLE
        activityMainBinding.tvError.text = msg
    }

    private fun getNewsData() {
        mainViewModel.getNewsDataList()
        newsAdapter = NewsAdapter()

        mainViewModel.newsData.observe(this) {
            it?.let {
                when (it) {
                    is Response.Success -> {
                        activityMainBinding.shimmerLayout.visibility = View.GONE
                        activityMainBinding.rvNews.visibility = View.VISIBLE
                        newsAdapter.submitList(it.data?.articles)
                    }
                    is Response.Error -> {
                        showError(it.toString())
                    }

                    is Response.Loading -> {
                        activityMainBinding.shimmerLayout.visibility = View.VISIBLE
                        activityMainBinding.rvNews.visibility = View.GONE
                    }
                }

            }
        }
        activityMainBinding.rvNews.layoutManager = LinearLayoutManager(this)
        activityMainBinding.rvNews.adapter = newsAdapter
    }
}