package com.example.hit.presentation.base.implementation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hit.presentation.base.interfaces.BaseView
import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    private var _baseActivity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        _baseActivity = activity as? BaseActivity
    }

    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun showErrorMessage(errorMessage: String) {
        baseViewActivity?.showErrorMessage(errorMessage)
    }

    override fun showProgressDialog() {
        baseViewActivity?.showProgressDialog()
    }

    override fun hideProgressDialog() {
        baseViewActivity?.hideProgressDialog()
    }

    override fun hideKeyBoard() {
        baseViewActivity?.hideKeyBoard()
    }

    override fun showNoInternetConnectionDialog() {
        baseViewActivity?.showNoInternetConnectionDialog()
    }

    override fun showSuccessDialog(message: String) {
        baseViewActivity?.showSuccessDialog(message)
    }

    override fun showSuccessDialog(messageRes: Int) {
        baseViewActivity?.showSuccessDialog(messageRes)
    }

    protected val baseViewActivity
        get() = _baseActivity as? BaseView
}