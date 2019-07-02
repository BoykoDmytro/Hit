package com.example.hit.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit.R
import com.example.hit.presentation.base.implementation.view.BaseFragment
import com.example.hit.ui.adapter.HitAdapter
import kotlinx.android.synthetic.main.fragment_hit.*

class HitFragment : BaseFragment() {


    override fun getLayoutId(): Int = R.layout.fragment_hit


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initRV()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initRV() {
        context?.let {
            hitRV.layoutManager = LinearLayoutManager(it)
            hitRV.adapter = HitAdapter()
        }
    }
}