package com.example.hit.presentation.base.implementation.view

import com.example.hit.presentation.base.interfaces.BaseView
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    override fun showErrorMessage(errorMessage: String) = Unit

    override fun showProgressDialog() {
    }

    override fun hideProgressDialog() {
    }

    override fun hideKeyBoard() = Unit

    override fun showNoInternetConnectionDialog() = Unit

    override fun showSuccessDialog(message: String) = Unit

    override fun showSuccessDialog(messageRes: Int) = showSuccessDialog(getString(messageRes))
}