package com.example.hit.ui.adapter

import android.view.View
import com.example.hit.data.models.Hit
import kotlinx.android.synthetic.main.viewholder_hit.view.*

class HitVH(view: View) : BaseVH<Hit>(view) {

    override fun bind(data: Hit, action: () -> Unit) {
        itemView.titleHitRV.text = data.title
        itemView.createdAtHitRV.text = getTime(data.createdAt)
        itemView.toogleHitRV.isChecked = false
    }

}