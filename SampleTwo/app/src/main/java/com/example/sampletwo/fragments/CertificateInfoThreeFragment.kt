package com.example.sampletwo.fragments

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Typeface
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificateInfoThreeBinding
import com.example.sampletwo.extension.customDialogTwoButton
import com.example.sampletwo.util.BitmapConverter
import com.example.sampletwo.util.IndentLeadingMarginSpan
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Suppress("DEPRECATION")
class CertificateInfoThreeFragment :
    BaseFragmentDataBinding<FragmentCertificateInfoThreeBinding, DataStoreViewModel>(R.layout.fragment_certificate_info_three) {

    override val viewModel: DataStoreViewModel by viewModels()

    override fun setUpBinding(view: View) {
        binding.vm = viewModel
        initRequestPermissionLauncher(view.context)
        initCameraLauncher()
        setUpBinding(view.context)
    }

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    private fun setUpBinding(context: Context) {
        val blueColor = context.getColor(R.color.confirm_btn_color)
        val redColor = context.getColor(R.color.red)

        binding.apply {
            textInfoCameraIdCardContent.run {
                val builder = SpannableStringBuilder(text).apply {
                    setSpan(ForegroundColorSpan(blueColor), 29, 35, 0)
                    setSpan(ForegroundColorSpan(blueColor), 38, 44, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 29, 35, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 38, 44, 0)
                }
                text = builder
            }
            textCaution.run {
                val builder = SpannableStringBuilder(text).apply {
                    setSpan(ForegroundColorSpan(redColor), 0, 2, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 0, 2, 0)
                }
                text = builder
            }
            textCautionContent.run {
                val builder = SpannableStringBuilder(text).apply {
                    setSpan(ForegroundColorSpan(blueColor), 1, 7, 0)
                    setSpan(ForegroundColorSpan(blueColor), 30, 37, 0)
                    setSpan(ForegroundColorSpan(blueColor), 39, 46, 0)
                    setSpan(ForegroundColorSpan(blueColor), 58, 64, 0)
                    setSpan(ForegroundColorSpan(blueColor), 80, 86, 0)
                    setSpan(ForegroundColorSpan(blueColor), 115, 122, 0)
                    setSpan(ForegroundColorSpan(blueColor), 155, 162, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 1, 7, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 30, 37, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 39, 46, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 58, 64, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 80, 86, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 115, 122, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 155, 162, 0)
                    setSpan(IndentLeadingMarginSpan("· "), 0, text.length, 0)
                }
                text = builder
            }
            btnCamera.setOnClickListener {
                requestPermission()
            }
        }
    }

    private fun initCameraLauncher() {
        cameraLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    viewModel.run {
                        bitmap.value =
                            BitmapConverter().bitmapToString(it.data?.extras?.get("data") as Bitmap)
                        viewModel.goSignUpFragment()
                    }
                }
            }
    }

    private fun cameraLauncher() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            cameraLauncher.launch(this)
        }
    }

    private fun requestPermission() {
        val permissionList = Manifest.permission.CAMERA
        requestPermissionLauncher.launch(permissionList)
    }

    private fun initRequestPermissionLauncher(context: Context) {
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it) cameraLauncher()
                else {
                    context.customDialogTwoButton(
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
}