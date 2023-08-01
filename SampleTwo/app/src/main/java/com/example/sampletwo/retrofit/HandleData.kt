package com.example.sampletwo.retrofit

import android.content.Context
import android.view.View
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.show
import com.example.sampletwo.extension.showToast

fun <T> handleData(
    loadingView: View,
    context: Context,
    event: ApiResult<T>,
    dataHandler: (T) -> Unit
) {
    when (event) {
        is ApiResult.Loading -> loadingView.show()
        is ApiResult.Success -> {
            dataHandler(event.data)
            loadingView.hide()
        }
        is ApiResult.Error -> {
            context.showToast("에러가 발생하였습니다.")
            loadingView.hide()
        }
    }
}