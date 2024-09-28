package com.example.enactmart.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.enactmart.R
import com.example.enactmart.databinding.ActivityShoppingBinding
import com.example.enactmart.util.Resource
import com.example.enactmart.viewmodel.CartViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ShoppingActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityShoppingBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<CartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Debug log for NavController
        Log.d("ShoppingActivity", "Setting up NavController")
        val navController = findNavController(R.id.shoppingHostFragment)
        Log.d("ShoppingActivity", "NavController retrieved: $navController")

        // Set up BottomNavigationView with NavController
        binding.bottomNavigation.setupWithNavController(navController)

        lifecycleScope.launchWhenStarted {
            viewModel.cartProducts.collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val count = resource.data?.size ?: 0
                        binding.bottomNavigation.getOrCreateBadge(R.id.cartFragment).apply {
                            number = count
                            backgroundColor = resources.getColor(R.color.cream)
                        }
                    }
                    else -> Unit
                }
            }
        }
    }
}
