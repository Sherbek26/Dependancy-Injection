package com.example.dependancyinjection.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dependancyinjection.api.PostRemoteDataSource
import com.example.dependancyinjection.database.PostEntity
import com.example.dependancyinjection.database.PostLocalDataSource
import com.example.dependancyinjection.models.Post
import com.example.dependancyinjection.utility.NetworkHelper
import com.example.dependancyinjection.utility.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val postLocalDataSource: PostLocalDataSource,
    private val postRemoteDataSource: PostRemoteDataSource
) {
    private val posts = MutableLiveData<NetworkResult<List<PostEntity>>>()

    init {
        getPosts()
    }

    private fun getPosts(){
        if (networkHelper.isNetworkConnected()){
            CoroutineScope(Dispatchers.IO).launch {
                posts.postValue(NetworkResult.loading())
                try {
                    coroutineScope {
                        val deferred : Deferred<List<Post>> = async { postRemoteDataSource.getAllPosts() }
                        val list = deferred.await()

                        val cachaList : MutableList<PostEntity> = ArrayList()
                        for (post in list){
                            val postEntity = PostEntity(post.id,post.userId,post.title,post.body)
                            cachaList.add(postEntity)
                        }
                        postLocalDataSource.cachePosts(cachaList)
                        posts.postValue(NetworkResult.success(cachaList))
                    }
                }catch (e : Exception){
                    posts.postValue(NetworkResult.error(e.message ?: "error"))
                }
            }
        }else{
            CoroutineScope(Dispatchers.IO).launch {
                posts.postValue(NetworkResult.success(postLocalDataSource.getAllPosts()))
            }
        }
    }
    fun getAllPosts() : LiveData<NetworkResult<List<PostEntity>>>{
        return posts
    }
}