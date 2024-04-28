package com.example.dependancyinjection.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dependancyinjection.database.PostEntity
import com.example.dependancyinjection.repository.PostRepository
import com.example.dependancyinjection.utility.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {
    fun getAllPosts() : LiveData<NetworkResult<List<PostEntity>>>{
        return repository.getAllPosts()
    }
}