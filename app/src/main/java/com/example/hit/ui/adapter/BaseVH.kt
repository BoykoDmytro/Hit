package com.example.hit.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseVH<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind (data : T,  action: () -> Unit)

}