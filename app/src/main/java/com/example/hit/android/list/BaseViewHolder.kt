package com.example.hit.android.list

import android.view.View
import com.example.hit.ui.listener.OnItemClickListener

abstract class BaseViewHolder<T>(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    open fun bindView(item: T, itemClickListener: OnItemClickListener<T>?) {
        itemView.setOnClickListener {
            itemClickListener?.onItemClick(item!!, adapterPosition)
        }
    }

    open fun unbindView() {
        itemView.setOnClickListener(null)
    }
}