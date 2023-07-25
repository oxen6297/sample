package com.example.sampletwo.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.sampletwo.datastore.IV
import com.example.sampletwo.datastore.SECRET_KEY
import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@RequiresApi(Build.VERSION_CODES.O)
class CipherUtil {

    fun encrypt(plainText: String): String {
        val key = SecretKeySpec(SECRET_KEY.toByteArray(),"AES")
        val iv = IvParameterSpec(IV.toByteArray())
        val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        val encrypted: ByteArray = cipher.doFinal(plainText.toByteArray(StandardCharsets.UTF_8))
        return String(Base64.getEncoder().encode(encrypted))
    }

    fun decrypt(cipherText: String?): String {
        val key = SecretKeySpec(SECRET_KEY.toByteArray(),"AES")
        val iv = IvParameterSpec(IV.toByteArray())
        val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key, iv)
        val decrypted: ByteArray = cipher.doFinal(Base64.getDecoder().decode(cipherText))
        return String(decrypted, StandardCharsets.UTF_8)
    }
}