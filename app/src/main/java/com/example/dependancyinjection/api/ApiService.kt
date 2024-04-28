package com.example.dependancyinjection.api

import com.example.dependancyinjection.models.Post
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts() : List<Post>
}