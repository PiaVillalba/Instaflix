package com.piavillalba.instaflix

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.piavillalba.instaflix.databinding.ActivityNavigationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navView = binding.bottomNavigation
        val navController = findNavController(R.id.navHostFragment)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.multimediaDetailFragment -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
    }

    private fun showBottomNavigation() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNavigation() {
        binding.bottomNavigation.visibility = View.GONE
    }
}
