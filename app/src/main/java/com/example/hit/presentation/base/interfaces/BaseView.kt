package com.example.hit.presentation.base.interfaces

import moxy.MvpView


interface BaseView: MvpView {

    fun showErrorMessage(errorMessage: String)

    fun showProgressDialog()

    fun hideProgressDialog()

    fun hideKeyBoard()

    fun showSuccessDialog(message: String)

    fun showSuccessDialog(messageRes: Int)

    fun showNoInternetConnectionDialog()
}