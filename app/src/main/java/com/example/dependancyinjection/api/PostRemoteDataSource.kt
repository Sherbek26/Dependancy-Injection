package com.example.dependancyinjection.api

import com.example.dependancyinjection.models.Post
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllPosts() : List<Post>{
        return apiService.getPosts()
    }
}