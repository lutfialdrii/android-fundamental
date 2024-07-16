package com.example.githubapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.R
import com.example.githubapp.data.remote.response.ItemsItem
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.ui.ListUserAdapter
import com.example.githubapp.ui.favorite.FavoriteActivity
import com.example.githubapp.ui.setting.SettingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        val layoutManager = LinearLayoutManager(this)
        activityMainBinding.rvUsers.layoutManager = layoutManager
        val itemDecor = DividerItemDecoration(this, layoutManager.orientation)
        activityMainBinding.rvUsers.addItemDecoration(itemDecor)

        mainViewModel.listUser.observe(this) { listUsers ->
            setData(listUsers)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        with(activityMainBinding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                mainViewModel.getUsers(searchView.text.toString())
                searchBar.setText(searchView.text)
                searchView.hide()
                Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                false
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_page -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
            R.id.setting_page -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }


        }
        return super.onOptionsItemSelected(item)
    }

    private fun setData(listUsers: List<ItemsItem>) {
        val adapter = ListUserAdapter()
        adapter.submitList(listUsers)
        activityMainBinding.rvUsers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            activityMainBinding.progressBar.visibility = View.VISIBLE
        } else {
            activityMainBinding.progressBar.visibility = View.INVISIBLE
        }
    }
}