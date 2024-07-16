package com.example.githubapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.data.local.entity.FavoriteUser
import com.example.githubapp.data.remote.response.DetailUserResponse
import com.example.githubapp.databinding.ActivityDetailUserBinding
import com.example.githubapp.utils.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var detailUserBinding: ActivityDetailUserBinding
    private val detailUserViewModel by viewModels<DetailViewModel>()
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private var isFavorite = false


    companion object {
        const val KEY_NAME = "username"
        const val KEY_AVATAR_URL = "avatar_url"
        private val TAB_TITLES =
            arrayOf("Followers", "Following")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        detailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(detailUserBinding.root)

        val username = intent.getStringExtra(KEY_NAME)
        val avatarUrl = intent.getStringExtra(KEY_AVATAR_URL)
        Log.d(KEY_NAME, username!!)

        detailUserViewModel.getUser(username)
        detailUserViewModel.detailUser.observe(this) {
            setData(it)
        }
        detailUserViewModel.isLoading.observe(this) {
            showLoading(it)
        }
//        favoriteViewModel.getData(username)
//        favoriteViewModel.getData.observe(this) {
//            Log.d("GetDATA", "onCreate: $it")
//            if (it != null) {
//                isFavorite = true
//                setFabIcon(isFavorite)
//            } else {
//                setFabIcon(isFavorite)
//            }
//        }

        favoriteViewModel.getAllData.observe(this) {
            it.map { fav ->
                isFavorite = fav.username == username
            }
            setFabIcon(isFavorite)
        }



        detailUserBinding.fabFavorite.setOnClickListener {
            isFavorite = if (isFavorite) {
                favoriteViewModel.delete(username)
                false
            } else {
                favoriteViewModel.insert(FavoriteUser(username, avatarUrl))
                true
            }

        }

//        Menghubungkan ViewPager2 dengan TabLayout
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = username
        val viewPager2: ViewPager2 = detailUserBinding.viewPager
        viewPager2.adapter = sectionsPagerAdapter

        val tabs: TabLayout = detailUserBinding.tabs
        TabLayoutMediator(tabs, viewPager2) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()

    }

    private fun setFabIcon(isFav: Boolean) {
        detailUserBinding.fabFavorite.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                if (isFav) R.drawable.ic_favorite
                else R.drawable.ic_favorite_border
            )
        )
    }

    private fun setData(user: DetailUserResponse) {
//        Log.d("userData", user.toString())
        Glide.with(this).load(user.avatarUrl).into(detailUserBinding.ivAvatar)
        detailUserBinding.tvName.text = user.name
        detailUserBinding.tvUsername.text = user.login
        detailUserBinding.tvBio.text = user.bio
        val newFollowers = this.resources.getString(R.string.followers, user.followers)
        val newFollowing = this.resources.getString(R.string.following, user.following)
        detailUserBinding.tvFollowers.text = newFollowers
        detailUserBinding.tvFollowing.text = newFollowing
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            detailUserBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailUserBinding.progressBar.visibility = View.INVISIBLE
        }
    }
}