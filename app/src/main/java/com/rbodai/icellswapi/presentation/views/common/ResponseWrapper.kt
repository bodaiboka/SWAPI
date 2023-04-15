package com.rbodai.icellswapi.presentation.views.common

import androidx.compose.runtime.Composable
import com.rbodai.icellswapi.presentation.viewstates.ListViewStateHolder

@Composable
fun <T> ResponseWrapper(res: ListViewStateHolder<T>, content: @Composable() (T) -> Unit) {
    if (res.isLoading) {
        LoadingMessage()
    }
    if (res.error.isNotBlank()) {
        ErrorMessage(error = res.error)
    }
    res.data?.let {
        content(it)
    }
}