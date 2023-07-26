package com.example.sampletwo.fragments

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
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
                    else findNavController().popBackStack()
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
                    return if (url.startsWith("intent://")) {
                        val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
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
            }
            loadUrl("https://dev.adtcapsmobilecard.co.kr/nice/checkplus_main?os_type=1&app_type=A")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.webView.destroy()
    }
}