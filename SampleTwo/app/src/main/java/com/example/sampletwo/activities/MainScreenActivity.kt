package com.example.sampletwo.activities

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.ActivityMainScreenBinding
import com.example.sampletwo.util.CustomDialog

class MainScreenActivity :
    BaseActivity<ActivityMainScreenBinding>(ActivityMainScreenBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view_main_screen) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavMainScreen.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, _ ->
            if (destination.id == R.id.qrLoginFragment) {
                controller.navigate(R.id.certificateFragment)
                CustomDialog(this, R.layout.custom_dialog).setDialog()
            }
        }
    }
}