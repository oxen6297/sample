package com.example.sampletwo.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import java.nio.charset.StandardCharsets
import java.security.KeyStore
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

@RequiresApi(Build.VERSION_CODES.O)
class CipherUtil(private val context: Context) {

    companion object {
        private const val KEY_STORE = "AndroidKeyStore"
        private const val AES_PADDING = "AES/ECB/PKCS7PADDING"
    }

    private fun generateKey(): SecretKey {
        val keyStore = KeyStore.getInstance(KEY_STORE).apply { load(null) }
        return if (keyStore.containsAlias(context.packageName)) {
            (keyStore.getEntry(context.packageName, null) as KeyStore.SecretKeyEntry).secretKey
        } else {
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEY_STORE)
            keyGenerator.init(
                KeyGenParameterSpec.Builder(
                    context.packageName,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                ).setBlockModes(KeyProperties.BLOCK_MODE_ECB)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .setRandomizedEncryptionRequired(false).build()
            )
            keyGenerator.generateKey()
        }
    }

    @SuppressLint("GetInstance")
    fun encrypt(plainText: String): String {
        val cipher = Cipher.getInstance(AES_PADDING)
        cipher.init(Cipher.ENCRYPT_MODE, generateKey())
        val encrypted: ByteArray = cipher.doFinal(plainText.toByteArray(StandardCharsets.UTF_8))
        return String(Base64.getEncoder().encode(encrypted))
    }

    @SuppressLint("GetInstance")
    fun decrypt(cipherText: String?): String {
        val cipher = Cipher.getInstance(AES_PADDING)
        cipher.init(Cipher.DECRYPT_MODE, generateKey())
        val decrypted: ByteArray = cipher.doFinal(Base64.getDecoder().decode(cipherText))
        return String(decrypted, StandardCharsets.UTF_8)
    }
}