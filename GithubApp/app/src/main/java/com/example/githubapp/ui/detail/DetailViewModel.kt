package com.example.githubapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.data.remote.response.DetailUserResponse
import com.example.githubapp.data.remote.response.ItemsItem
import com.example.githubapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _detailUser = MutableLiveData<DetailUserResponse>()
    val detailUser: LiveData<DetailUserResponse> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoading2 = MutableLiveData<Boolean>()
    val isLoading2: LiveData<Boolean> = _isLoading2

    private val _listFollow = MutableLiveData<List<ItemsItem>>()
    val listFollow: LiveData<List<ItemsItem>> = _listFollow

    companion object {
        private const val TAG = "DetailViewModel"
    }

    fun getUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : retrofit2.Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _detailUser.value = response.body()
//                    Log.d(TAG, _detailUser.value.toString())
                } else {
                    Log.d(TAG, "OnFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "OnFailure : ${t.message}")
            }

        })
    }

    fun getFollowers(username: String) {
        _isLoading2.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : retrofit2.Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading2.value = false
                if (response.isSuccessful && response.body() != null) {
                    _listFollow.value = response.body()
                } else {
                    Log.d(TAG, "OnFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading2.value = false
                Log.d(TAG, "OnFailure : ${t.message}")
            }

        })
    }

    fun getFollowing(username: String) {
        _isLoading2.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : retrofit2.Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading2.value = false
                if (response.isSuccessful && response.body() != null) {
                    _listFollow.value = response.body()
                } else {
                    Log.d(TAG, "OnFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading2.value = false
                Log.d(TAG, "OnFailure : ${t.message}")
            }

        })
    }


}