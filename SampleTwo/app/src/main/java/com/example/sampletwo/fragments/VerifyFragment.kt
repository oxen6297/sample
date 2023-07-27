package com.example.sampletwo.fragments

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebView.setWebContentsDebuggingEnabled
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentVerifyBinding
import com.example.sampletwo.extension.customDialogTwoButton
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.show
import com.example.sampletwo.viewmodels.MainViewModel

class VerifyFragment :
    BaseFragment<FragmentVerifyBinding, MainViewModel>(R.layout.fragment_verify) {

    override val viewModel: MainViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val backPressedCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.webView.apply {
                    if (canGoBack()) goBack()
                    else {
                        context.customDialogTwoButton(
                            R.string.confirm_btn_text,
                            R.string.cancel,
                            R.string.verify,
                            R.string.verify_dialog_content,
                            { findNavController().popBackStack() }
                        )
                    }
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallBack)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setUpBinding(view: View) {
        binding.webView.apply {
            settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                domStorageEnabled = true
                javaScriptCanOpenWindowsAutomatically = true
                setSupportMultipleWindows(true)
                setWebContentsDebuggingEnabled(true)
                cacheMode = WebSettings.LOAD_NO_CACHE
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    val url = request?.url.toString()
                    val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                    return if (url.startsWith("intent://")) {
                        try {
                            startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            if (intent.getPackage().isNullOrBlank()) {
                                startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("market://details?id=${intent.getPackage()}")
                                    )
                                )
                            }
                        }
                        true
                    } else false
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    requireActivity().window.setFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                    binding.progressbar.show()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressbar.hide()
                    requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                }
            }
            loadUrl("https://dev.adtcapsmobilecard.co.kr/nice/checkplus_main?os_type=1&app_type=A")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.webView.destroy()
    }
}