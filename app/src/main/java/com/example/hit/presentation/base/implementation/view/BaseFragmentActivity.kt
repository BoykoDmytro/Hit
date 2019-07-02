package com.example.hit.presentation.base.implementation.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.example.hit.R

abstract class BaseFragmentActivity : BaseActivity() {

    private var currentFragment: BaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }

    open fun showFragment(fragment: BaseFragment, addToBackStack: Boolean = false) {
        val tag = fragment.javaClass.canonicalName
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_holder, fragment, tag)
        transaction.takeIf { addToBackStack }?.addToBackStack(tag)
        transaction.commit()
        currentFragment = fragment
    }

    @LayoutRes
    protected open val layoutRes = R.layout.activity_with_fragment

}