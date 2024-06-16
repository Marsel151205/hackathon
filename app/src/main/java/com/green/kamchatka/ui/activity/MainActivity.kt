package com.green.kamchatka.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.green.kamchatka.R
import com.green.kamchatka.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupToolbarAndBottomNavigation()
    }

    private fun setupToolbarAndBottomNavigation() {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.loginFragment, R.id.registrationFragment, R.id.resultFragment, R.id.bookingFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                    binding.toolbar.visibility = View.GONE
                }
                R.id.mainFragment -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                    binding.toolbar.visibility = View.VISIBLE
                    binding.tvTitleScreen.text = "Парки"
//                    binding.icBack.visibility = View.GONE
                }
                R.id.profileFragment -> {
                    binding.tvTitleScreen.text = "Личный кабинет"
                }
                R.id.chatFragment -> {
                    binding.tvTitleScreen.text = "Обратная связь"
                }
                R.id.trailsFragment -> {
                    binding.tvTitleScreen.text = "Тропы"
                }
            }
        }
    }
}