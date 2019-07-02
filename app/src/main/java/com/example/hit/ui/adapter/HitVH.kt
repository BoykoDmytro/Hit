package com.example.hit.ui.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.appcompat.widget.SwitchCompat
import com.example.hit.data.models.Hit
import com.example.hit.ui.listener.OnItemClickListener
import kotlinx.android.synthetic.main.viewholder_hit.view.*

class HitVH(
    view: View, onItemClickListener: OnItemClickListener<Hit>,
    private val selectedListener: OnCheckBoxSelectionListener
) : BaseSelectableViewHolder<Hit>(view),
    CompoundButton.OnCheckedChangeListener {

    override fun bind(data: Hit, isSelected: Boolean, action: () -> Unit) {
        itemView.titleHitRV.text = data.title
        itemView.createdAtHitRV.text = getTime(data.createdAt)
        itemView.toogleHitRV.isChecked = false
        itemView.toogleHitRV.setOnCheckedChangeListener(this)
    }

    override fun getCheckBoxItem(): SwitchCompat = itemView.toogleHitRV

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        selectedListener.selectionClick(adapterPosition, isChecked)
    }

    fun bind(payloads: MutableList<Any>) {
        payloads.takeIf { payloads.isNotEmpty() }?.let { it[0] as? Boolean }?.let {
            itemView.toogleHitRV.setOnCheckedChangeListener(null)
            itemView.toogleHitRV.isChecked = it
            itemView.toogleHitRV.setOnCheckedChangeListener(this)
        }
    }
}