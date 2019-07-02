package com.example.hit.data.http

sealed class PendingResult

data class Success<T>(val body: T?): PendingResult()

data class ErrorResult(val code: Int, val message: String?): PendingResult()