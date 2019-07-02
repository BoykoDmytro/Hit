package com.example.hit.ui.fragment

import com.example.hit.data.models.Hit
import com.example.hit.presentation.base.implementation.presenter.BasePresenter
import moxy.InjectViewState

@InjectViewState
class HitPresenter : BasePresenter<HitView>() {

    fun getItems(){

    }

    fun deleteItems(selectedItems: List<Hit>) {

    }
}