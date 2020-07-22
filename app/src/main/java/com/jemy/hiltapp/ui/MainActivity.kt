package com.jemy.hiltapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.jemy.hiltapp.R
import com.jemy.hiltapp.model.Blog
import com.jemy.hiltapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getBlogs()
        observeBlogs()
    }

    private fun observeBlogs() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {

                is DataState.Success<List<Blog>> -> {
                    displayProgressbar(false)
                    displayBlogs(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressbar(false)
                    displayError(dataState.e.message)
                }
                is DataState.Loading -> {
                    displayProgressbar(true)

                }
            }
        })
    }

    @ExperimentalCoroutinesApi
    private fun getBlogs() {
        viewModel.setStatEvent(MainStatEvent.GetBlogEvents)
    }

    private fun displayError(message: String? = "unknown error") {
        message.let { myText.text = it }
    }

    private fun displayBlogs(blogList: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogList) {
            sb.append(blog.title + "\n")
        }
        myText.text = sb.toString()
    }

    private fun displayProgressbar(isDisplayed: Boolean) {
        progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }
}