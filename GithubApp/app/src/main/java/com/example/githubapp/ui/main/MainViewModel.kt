package com.example.githubapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.data.remote.response.GithubResponse
import com.example.githubapp.data.remote.response.ItemsItem
import com.example.githubapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser: LiveData<List<ItemsItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        getUsers()
    }

    fun getUsers(q: String = "Lutfi") {
//        Log.d(TAG, "GET USERS")
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsers(q)
        client.enqueue(object : retrofit2.Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.items
//                    Log.d(TAG, listUser.value?.size.toString())
                } else {
                    Log.d(TAG, "OnFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "OnFailure : ${t.message}")
            }
        })
    }
}