package com.example.sampletwo.activities

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.ActivityMainScreenBinding
import com.example.sampletwo.extension.customDialog
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenActivity :
    BaseActivity<ActivityMainScreenBinding>(ActivityMainScreenBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view_main_screen) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavMainScreen.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, _ ->
            when (destination.id) {
                R.id.qrLoginFragment -> {
                    controller.popBackStack()
                    customDialog(R.string.dialog_certificate_text,R.string.confirm_btn_text)
                    binding.bottomNavMainScreen.show()
                }
                R.id.certificateFragment -> binding.bottomNavMainScreen.show()
                R.id.showMoreFragment -> binding.bottomNavMainScreen.show()
                else -> binding.bottomNavMainScreen.hide()
            }
        }
    }
}