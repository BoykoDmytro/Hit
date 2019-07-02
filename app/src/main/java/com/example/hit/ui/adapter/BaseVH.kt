package com.example.hit.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseVH<T>(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        private const val mailCurFormat = "yyyy-MM-dd'T'HH:mm:ss+SSSS"
        private const val mailRequiredFormet = "h:mm a"
    }

    abstract fun bind(data: T, action: () -> Unit)

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