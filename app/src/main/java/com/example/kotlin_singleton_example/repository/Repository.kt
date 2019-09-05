package com.example.kotlin_singleton_example.repository

import androidx.lifecycle.LiveData
import com.example.kotlin_singleton_example.api.MyRetrofitBuilder
import com.example.kotlin_singleton_example.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {
    var job: CompletableJob? = null
    fun getUser(userId: String): LiveData<User> {
        job = Job()
        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job.let { theJob ->
                    CoroutineScope(IO + theJob!!).launch {
                        val user = MyRetrofitBuilder.apiService.getUser(userId)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}