package com.example.hit.android.list

interface ItemClickListener<T> {

    fun onItemClick(item: T, position: Int)

}