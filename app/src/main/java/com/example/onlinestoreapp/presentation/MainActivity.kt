package com.example.onlinestoreapp.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.appComponent
import com.example.onlinestoreapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(binding.root)
        //ViewModelProvider(this).get<MainViewModel>().inject(this)
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        setSupportActionBar(binding.toolbar)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.Home,
                R.id.Catalog,
                R.id.Shop,
                R.id.Discount,
                R.id.Profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        val navOptions: NavOptions = NavOptions.Builder()
            .setPopUpTo(R.id.startFragment, true)
            .build()

        viewModel.userLiveData.observe(this) { hasUser ->
            if (hasUser == null)
                navController.navigate(R.id.registrationFragment, null, navOptions)
            else
                navController.navigate(R.id.Home, null, navOptions)
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.favouriteFragment -> {
                    binding.toolbar.isTitleCentered = false
                }

                R.id.registrationFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    binding.bottomNavigation.visibility = View.GONE
                }

                else -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                    binding.toolbar.isTitleCentered = true
                }
            }
        }
    }
}