package com.example.sampletwo.activities

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.sampletwo.databinding.ActivityUrlschemeBinding
import com.example.sampletwo.util.CipherUtil

@RequiresApi(Build.VERSION_CODES.O)
class URLSchemeActivity :
    BaseActivity<ActivityUrlschemeBinding>(ActivityUrlschemeBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val action: String? = intent?.action
        val data: Uri? = intent?.data
        if (action == Intent.ACTION_VIEW) {
            val text = data?.getQueryParameter("text")
            val deCryptText = CipherUtil().decrypt(text)
            binding.textScheme.text = deCryptText
        }
    }
}