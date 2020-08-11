package com.example.mutlubievcase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mutlubievcase.R
import com.example.mutlubievcase.models.BasicViewPagerData

class BasicViewPagerAdapter (private var basicList: List<BasicViewPagerData>): RecyclerView.Adapter<BasicViewPagerAdapter.ViewPagerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_basic_viewpager,parent,false))
    }

    override fun getItemCount()=basicList.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        basicList[position].apply {
            holder.itemTitle.text = title
            holder.itemDetails.text = details
            holder.itemImage.setImageResource(image)
        }
    }

    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemTitle: TextView = itemView.findViewById(R.id.tsiTitle)
        val itemDetails: TextView = itemView.findViewById(R.id.tsiDetails)
        val itemImage: ImageView = itemView.findViewById(R.id.tsiImage)
    }
}