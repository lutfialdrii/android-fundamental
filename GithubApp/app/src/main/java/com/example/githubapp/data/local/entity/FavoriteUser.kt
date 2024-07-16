package com.example.githubapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteUser(
    @PrimaryKey(autoGenerate = false)
    var username: String = "",
    @field:ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null
)
