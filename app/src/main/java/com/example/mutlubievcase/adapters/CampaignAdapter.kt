package com.example.mutlubievcase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mutlubievcase.R
import com.example.mutlubievcase.models.CampaignViewPagerData

class CampaignAdapter (private var campaignList: List<CampaignViewPagerData>): RecyclerView.Adapter<CampaignAdapter.CampaignViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        return CampaignViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_campaign,parent,false))
    }

    override fun getItemCount()=campaignList.size

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {
        campaignList[position].apply {
            holder.itemTitle.text = title
            holder.itemImage.setImageResource(image)
        }
    }

    inner class CampaignViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemTitle: TextView = itemView.findViewById(R.id.campaignTitle)
        val itemImage: ImageView = itemView.findViewById(R.id.campaignImage)
    }
}