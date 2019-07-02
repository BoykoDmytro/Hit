package com.example.hit.ui.fragment

import com.example.hit.data.models.Hit
import com.example.hit.presentation.base.interfaces.BaseView

interface HitView : BaseView {

    fun hideDeleteMode()

    fun addNewItems(data : ArrayList<Hit>)
}