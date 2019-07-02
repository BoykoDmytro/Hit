package com.example.hit.ui.adapter

import android.view.View
import android.widget.CheckBox
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hit.android.list.BaseViewHolder
import com.example.hit.ui.listener.OnItemClickListener
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseSelectableViewHolder<T>(view: View, val listener: OnItemClickListener<T>? = null) :
    BaseViewHolder<T>(view) {

    companion object {
        private const val mailCurFormat = "yyyy-MM-dd'T'HH:mm:ss+SSSS"
        private const val mailRequiredFormet = "h:mm a"
    }


    abstract fun bind(data: T, isSelected: Boolean, action: () -> Unit)

    abstract fun getCheckBoxItem(): SwitchCompat

    interface OnCheckBoxSelectionListener {

        fun selectionClick(position: Int, checked: Boolean)
    }

    fun getTime(time: String?): String {
        var transformDate: String? = null
        time?.let {
            try {
                val date = SimpleDateFormat(mailCurFormat, Locale.ENGLISH).parse(time)
                transformDate = SimpleDateFormat(mailRequiredFormet, Locale.ENGLISH).format(date)
            } catch (ignored: ParseException) {
            }
        } ?: return ""

        return transformDate ?: time
    }
}