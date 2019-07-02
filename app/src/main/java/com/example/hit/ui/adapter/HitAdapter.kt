package com.example.hit.ui.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hit.R
import com.example.hit.data.models.Hit
import com.example.hit.ui.listener.OnChangedToolbarListener
import com.example.hit.ui.listener.OnItemClickListener

class HitAdapter(
    private var data: ArrayList<Hit>?,
    val listener: OnItemClickListener<Hit>,
    private val onChangedToolbarListener: OnChangedToolbarListener
) : RecyclerView.Adapter<HitVH>(), OnItemClickListener<Hit>,
    BaseSelectableViewHolder.OnCheckBoxSelectionListener {

    private val selectedItems = SparseBooleanArray()
    var isInChoiceMode: Boolean = false
        set(value) {
            field = value
            if (!value) {
                selectedItems.clear()
                data?.let { notifyItemRangeChanged(0, it.size, false) }
            }
        }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HitVH {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.viewholder_hit, p0, false)
        return HitVH(view, this, this)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(p0: HitVH, p1: Int) {
        data?.let {
            p0.bind(data!![p1], selectedItems.get(p1)) { onItemClick(it[p1], p1) }
        }
    }

    fun setData(data: ArrayList<Hit>?) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onItemClick(item: Hit, position: Int) {
        listener.onItemClick(item, position)
    }

    override fun selectionClick(position: Int, checked: Boolean) {
        selectedItems.delete(position)
        if (checked) selectedItems.put(position, true)
        val isInChoiceMode = selectedItems.size() != 0
        if (this.isInChoiceMode != isInChoiceMode) {
            this.isInChoiceMode = isInChoiceMode
            onChangedToolbarListener.changedToolBar(isInChoiceMode)
        }
        onChangedToolbarListener.updateSelectedItemCount(selectedItems.size())
    }

    fun getSelectedItems(): List<Hit> {
        val items = arrayListOf<Hit>()
        for (i in 0 until selectedItems.size()) {
            val data = data?.get(selectedItems.keyAt(i))
            data?.let { items.add(it) }
        }
        return items
    }

    override fun getItemId(position: Int): Long =
        data?.get(position)?.objectID?.hashCode()?.toLong() ?: super.getItemId(position)

    fun addItems(data: java.util.ArrayList<Hit>) {
        val count = this.data?.size ?: 0
        this.data?.addAll(data)
        this.notifyItemRangeInserted(count, this.data?.size ?: 0)
    }
}
