package com.example.sampletwo.fragments

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebView.setWebContentsDebuggingEnabled
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentVerifyBinding
import com.example.sampletwo.viewmodels.MainViewModel


// Webview 구현 및 관련 옵션 세팅
// ADT NICE 본인 인증 url 호출
//- js 허용
//- js alert 노출
//- session
//- cookie
//- backpress(history back)

class VerifyFragment :
    BaseFragment<FragmentVerifyBinding, MainViewModel>(R.layout.fragment_verify) {

    override val viewModel: MainViewModel by viewModels()

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
            setOnKeyListener { _, keyCode, event ->
                if (event.action != KeyEvent.ACTION_DOWN) return@setOnKeyListener true
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (canGoBack()) goBack()
                    else findNavController().popBackStack()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.webView.destroy()
    }
}