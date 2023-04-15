package com.rbodai.icellswapi.presentation.viewstates

data class ListViewStateHolder<T> (
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String = ""
)