package com.example.githubapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.githubapp.data.local.entity.FavoriteUser

@Dao
interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favUserEntity: FavoriteUser)

    @Update
    fun update(favUserEntity: FavoriteUser)

    @Query("DELETE FROM FavoriteUser WHERE username = :username")
    fun delete(username: String)

    @Query("SELECT * from favoriteuser ORDER BY username ASC")
    fun getAllFavorites(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM FavoriteUser WHERE username = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUser>
}