package com.example.githubapp.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.R
import com.example.githubapp.data.local.entity.FavoriteUser
import com.example.githubapp.databinding.ActivityFavoriteBinding
import com.example.githubapp.ui.detail.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var favoriteBinding: ActivityFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        favoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val layoutManager = LinearLayoutManager(this)
        favoriteBinding.rvUsers.layoutManager = layoutManager
        val itemDecor = DividerItemDecoration(this, layoutManager.orientation)
        favoriteBinding.rvUsers.addItemDecoration(itemDecor)

        favoriteViewModel.getAllData.observe(this) {
            setData(it)
        }
    }

    private fun setData(listFavorite: List<FavoriteUser>) {
        val adapter = ListFavoriteAdapter()
        adapter.submitList(listFavorite)
        favoriteBinding.rvUsers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            favoriteBinding.progressBar.visibility = View.VISIBLE
        } else {
            favoriteBinding.progressBar.visibility = View.INVISIBLE
        }
    }
}