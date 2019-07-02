package com.example.hit.android.list.pagination

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.hit.android.list.BaseViewHolder
import com.example.hit.android.list.ItemClickListener
import com.example.hit.ui.listener.OnItemClickListener

abstract class PagedListRVAdapter<T, VH : BaseViewHolder<T>>(
    protected val diffCallback: DiffUtil.ItemCallback<T>,
    protected var itemClickListener: OnItemClickListener<T>? = null
) : PagedListAdapter<T, VH>(diffCallback) {

    override fun onBindViewHolder(p0: VH, position: Int) = p0.bindView(getItem(position)!!, itemClickListener)

    override fun onViewRecycled(holder: VH) = holder.unbindView()
}