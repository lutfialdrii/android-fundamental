package com.example.githubapp.data.repository

import androidx.lifecycle.LiveData
import com.example.githubapp.data.local.entity.FavoriteUser
import com.example.githubapp.data.local.room.FavoriteUserDao

class FavoriteUserRepository(private val mFavoriteUserDao: FavoriteUserDao) {

    fun getAllData(): LiveData<List<FavoriteUser>> = mFavoriteUserDao.getAllFavorites()
    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUser> =
        mFavoriteUserDao.getFavoriteUserByUsername(username)

    fun insert(favoriteUser: FavoriteUser) {
        mFavoriteUserDao.insert(favoriteUser)

    }

    fun delete(username: String) {
        mFavoriteUserDao.delete(username)
    }
}