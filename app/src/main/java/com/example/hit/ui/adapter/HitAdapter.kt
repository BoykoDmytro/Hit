package com.example.hit.ui.adapter

import android.annotation.SuppressLint
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.hit.R
import com.example.hit.android.list.pagination.PagedListRVAdapter
import com.example.hit.presentation.posts.interfaces.PostViewModel
import com.example.hit.ui.listener.OnChangedToolbarListener
import com.example.hit.ui.listener.OnItemClickListener

class HitAdapter(
    val listener: OnItemClickListener<PostViewModel>,
    private val onChangedToolbarListener: OnChangedToolbarListener
) : PagedListRVAdapter<PostViewModel, HitVH>(DIFF_CALBACK, listener), OnItemClickListener<PostViewModel>,
    BaseSelectableViewHolder.OnCheckBoxSelectionListener {

    companion object {
        private val DIFF_CALBACK = object : DiffUtil.ItemCallback<PostViewModel>() {
            override fun areItemsTheSame(oldItem: PostViewModel, newItem: PostViewModel): Boolean {
                return oldItem.title == newItem.title
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: PostViewModel, newItem: PostViewModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val selectedItems = SparseBooleanArray()
    var isInChoiceMode: Boolean = false
        set(value) {
            field = value
            if (!value) {
                selectedItems.clear()
                currentList?.let { notifyItemRangeChanged(0, it.size, false) }
            }
        }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HitVH {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.viewholder_hit, p0, false)
        return HitVH(view, this, this)
    }

    override fun onBindViewHolder(p0: HitVH, p1: Int) {
        currentList?.let {
            p0.bind(it[p1]!!, selectedItems.get(p1)) { onItemClick(it[p1]!!, p1) }
        }
    }

    override fun onItemClick(item: PostViewModel, position: Int) {
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

    fun getSelectedItems(): List<PostViewModel> {
        val items = arrayListOf<PostViewModel>()
        for (i in 0 until selectedItems.size()) {
            val data = currentList?.get(selectedItems.keyAt(i))
            data?.let { items.add(it) }
        }
        return items
    }

    override fun getItemId(position: Int): Long =
        currentList?.get(position)?.title?.hashCode()?.toLong() ?: super.getItemId(position)

    fun addItems(data: ArrayList<PostViewModel>) {
        val count = this.currentList?.size ?: 0
        this.currentList?.addAll(data)
        this.notifyItemRangeInserted(count, this.currentList?.size ?: 0)
    }
}
