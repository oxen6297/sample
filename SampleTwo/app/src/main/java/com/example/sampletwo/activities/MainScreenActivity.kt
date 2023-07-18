package com.example.sampletwo.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.ActivityMainScreenBinding
import com.example.sampletwo.extension.customDialogTwoButton
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.qrPopUp
import com.example.sampletwo.extension.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenActivity :
    BaseActivity<ActivityMainScreenBinding>(ActivityMainScreenBinding::inflate) {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            initRequestPermissionLauncher(this)
            requestPermission()
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view_main_screen) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavMainScreen.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, _ ->
            when (destination.id) {
                R.id.qrLoginFragment -> {
                    qrPopUp {
                        controller.popBackStack()
                        binding.bottomNavMainScreen.show()
                    }
                }

                R.id.certificateFragment -> binding.bottomNavMainScreen.show()
                R.id.showMoreFragment -> binding.bottomNavMainScreen.show()
                else -> binding.bottomNavMainScreen.hide()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestPermission() {
        val notificationPermission = Manifest.permission.POST_NOTIFICATIONS
        requestPermissionLauncher.launch(notificationPermission)
    }

    private fun initRequestPermissionLauncher(context: Context) {
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (!it) context.customDialogTwoButton(
                    R.string.go_setting,
                    R.string.dialog_cancel,
                    R.string.permission_title,
                    R.string.permission_content,
                    {
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.parse("package:" + context.packageName)
                            startActivity(this)
                        }
                    })

            }
    }
}