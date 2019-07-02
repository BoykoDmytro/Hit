package com.example.hit.data.pagination

import androidx.paging.PositionalDataSource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PaginationDataSource<T>(
    private val arguments: MutableMap<String, Any?> = mutableMapOf(
        PAGE_NUMBER_KEY to INITIAL_PAGE
    ),
    private val retriever: suspend (Map<String, Any?>) -> List<T>
) : PositionalDataSource<T>() {

    companion object {
        private const val INITIAL_POSITION = 0
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        GlobalScope.launch {
            val result = withContext(IO) { retriever(arguments) }
            callback.onResult(result, INITIAL_POSITION)
        }
    }

    operator fun set(key: String, value: Any?) {
        arguments[key] = value
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        GlobalScope.launch {

            val pageNumber = with(params) { (startPosition / loadSize).inc() }
            val isNextUploadAvailable = with(params) { startPosition % loadSize == 0 }

            val result: List<T> = when (isNextUploadAvailable) {
                true -> when {
                    pageNumber > INITIAL_POSITION -> retriever(arguments)
                    else -> listOf()
                }
                else -> listOf()
            }
            callback.onResult(result)
        }
    }
}