package com.example.hit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.driver.temp.Hit
import com.example.hit.R
import com.example.hit.data.models.HitVH

class HitAdapter(var data: ArrayList<Hit>?) : RecyclerView.Adapter<HitVH>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HitVH  =
        HitVH(LayoutInflater.from(p0.context).inflate(R.layout.viewholder_hit,  p0, false))

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(p0: HitVH, p1: Int) {
        data?.let { p0.bind(it[p1]) {} }
    }
}
