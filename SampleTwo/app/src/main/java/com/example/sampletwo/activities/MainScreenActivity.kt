package com.example.sampletwo.activities

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.ActivityMainScreenBinding
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.permissionPopUp
import com.example.sampletwo.extension.qrPopUp
import com.example.sampletwo.extension.show
import com.example.sampletwo.extension.showToast
import com.example.sampletwo.util.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenActivity :
    BaseActivity<ActivityMainScreenBinding>(ActivityMainScreenBinding::inflate) {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NetworkConnection(this).observe(this) {
            if (it) showToast("네트워크가 연결되었습니다.")
            else showToast("네트워크 연결을 확인해주세요.")
        }

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
                if (!it) context.permissionPopUp()
            }
    }
}