package com.example.enactmart.fragments.shopping

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.enactmart.R
import com.example.enactmart.databinding.FragmentHomeBinding
import com.example.enactmart.fragments.categories.AccessoryFragment
import com.example.enactmart.fragments.categories.AgniFragment
import com.example.enactmart.fragments.categories.AnnotsavFragment
import com.example.enactmart.fragments.categories.ClairFragment
import com.example.enactmart.fragments.categories.MainCategoryFragment
import com.example.enactmart.fragments.categories.UrjaFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf(
            MainCategoryFragment(),
            AgniFragment(),
            AnnotsavFragment(),
            ClairFragment(),
            AccessoryFragment(),
            UrjaFragment()
        )

        binding.viewpagerHome.isUserInputEnabled = false

        val viewPager2Adapter =
            HomeViewpagerAdapter(categoriesFragments, childFragmentManager, lifecycle)
        binding.viewpagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Agni"
                2 -> tab.text = "Annotsav"
                3 -> tab.text = "Clair"
                4 -> tab.text = "Accessory"
                5 -> tab.text = "URJA"
            }
        }.attach()
    }
}