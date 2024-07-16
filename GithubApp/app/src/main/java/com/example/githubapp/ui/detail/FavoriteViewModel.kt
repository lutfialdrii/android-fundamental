package com.example.githubapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubapp.data.local.entity.FavoriteUser
import com.example.githubapp.data.local.room.FavoriteUserDatabase
import com.example.githubapp.data.repository.FavoriteUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val mFavoriteUserRepository: FavoriteUserRepository
    private val _getData = MutableLiveData<FavoriteUser>()
    val getData: LiveData<FavoriteUser> = _getData
    val getAllData: LiveData<List<FavoriteUser>>

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    init {
        val favoriteUserDao = FavoriteUserDatabase.getInstance(application).favUserDao()
        mFavoriteUserRepository = FavoriteUserRepository(favoriteUserDao)
        getAllData = mFavoriteUserRepository.getAllData()
    }

    fun getData(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mFavoriteUserRepository.getFavoriteUserByUsername(username)
        }
    }

    fun insert(favoriteUser: FavoriteUser) {
        viewModelScope.launch(Dispatchers.IO) {
            mFavoriteUserRepository.insert(favoriteUser)
        }
    }

    fun delete(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mFavoriteUserRepository.delete(username)
        }
    }


}