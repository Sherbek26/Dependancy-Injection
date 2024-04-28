package com.example.dependancyinjection.database

import androidx.lifecycle.LiveData
import com.example.dependancyinjection.models.Post
import javax.inject.Inject

class PostLocalDataSource @Inject constructor(private val postDao: PostDao) {

    suspend fun cachePosts(list: List<PostEntity>){
        postDao.cacheAllPosts(list)
    }

    suspend fun getAllPosts() : List<PostEntity>{
        return postDao.getAllPosts()
    }
}