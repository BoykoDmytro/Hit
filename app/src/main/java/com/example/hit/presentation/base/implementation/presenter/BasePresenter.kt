package com.example.hit.presentation.base.implementation.presenter

import com.example.hit.data.http.CONNECTION_ERROR
import com.example.hit.data.http.ErrorResult
import com.example.hit.data.http.PendingResult
import com.example.hit.data.http.Success
import com.example.hit.presentation.base.interfaces.BaseView
import kotlinx.coroutines.*
import moxy.MvpPresenter
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.coroutines.CoroutineContext

open class BasePresenter<T : BaseView> : MvpPresenter<T>(), CoroutineScope {

    companion object {
        private val EXCEPTION_HANDLER = CoroutineExceptionHandler { _, exception ->
            StringWriter().let {
                PrintWriter(it).use { exception.printStackTrace(it) }
            }
        }
    }

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + EXCEPTION_HANDLER

    fun executeOnUI(block: suspend () -> Unit) = launch(coroutineContext) {
        block()
    }

    suspend fun <T> executePending(block: suspend () -> T): T {
        try {
            viewState.showProgressDialog()
            return withContext(Dispatchers.IO) { block() }
        } finally {
            viewState.hideProgressDialog()
        }
    }

    suspend fun <T> executeApiRequest(block: suspend () -> PendingResult): T? = withContext(Dispatchers.IO) {
        val result = block()
        when (result) {
            is ErrorResult -> null.also {
                executeOnUI {
                    when (result.code) {
                        CONNECTION_ERROR -> viewState.showNoInternetConnectionDialog()
                        else -> result.message?.let { viewState.showErrorMessage(it) }
                    }
                }
            }
            is Success<*> -> result.body as? T?
        }
    }

    suspend fun <T> executeApiRequestWithDialog(block: suspend () -> PendingResult) = executePending<T?> {
        executeApiRequest(block)
    }
}