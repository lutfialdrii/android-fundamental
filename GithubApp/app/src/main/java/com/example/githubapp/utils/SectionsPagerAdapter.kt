package com.example.githubapp.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubapp.ui.ListDetailFollowFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    var username: String = ""
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ListDetailFollowFragment()
        fragment.arguments = Bundle().apply {
            putInt(ListDetailFollowFragment.ARG_POSITION, position + 1)
            putString(ListDetailFollowFragment.ARG_USERNAME, username)
        }
        return fragment
    }

}