package com.example.dependancyinjection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.dependancyinjection.databinding.ActivityMainBinding
import com.example.dependancyinjection.utility.NetWorkStatus
import com.example.dependancyinjection.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAllPosts().observe(this){
            when(it.status){
                NetWorkStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                NetWorkStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.text.text = it.data.toString()
                }
                NetWorkStatus.ERROR -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.text.text = it.message.toString()
                }
            }
        }

    }
}