package com.example.mutlubievcase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mutlubievcase.R
import com.example.mutlubievcase.interfaces.OnBasicCardClick
import com.example.mutlubievcase.models.BasicCardData

class BasicCardRecyclerViewAdapter(private val list:List<BasicCardData>, private val onBasicCardClick: OnBasicCardClick): RecyclerView.Adapter<BasicCardRecyclerViewAdapter.BasicCardViewHolder>() {

    var selectedIndex=-1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicCardViewHolder {
        return BasicCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_basic_card,parent,false))
    }

    override fun getItemCount()=list.count()

    override fun onBindViewHolder(holder: BasicCardViewHolder, position: Int) {
        holder.itemText.text=list[position].text
        val backgroundColor= if (selectedIndex==position) android.R.color.holo_red_light else android.R.color.white
        val textColor= if (selectedIndex==position) android.R.color.white else android.R.color.black

        holder.itemText.setTextColor(holder.itemView.resources.getColor(textColor,holder.itemView.context.theme))
        holder.cardView.setCardBackgroundColor(holder.itemView.resources.getColor(backgroundColor,holder.itemView.context.theme))

        holder.itemView.setOnClickListener {
            var previousSelected: Int?=null
            if (selectedIndex!=-1)
                previousSelected=selectedIndex

            selectedIndex=position
            notifyDataSetChanged()
            onBasicCardClick.onBasicCardSelected(position,list[position],previousSelected,if (previousSelected!=null) list[previousSelected] else null)
        }
    }
    inner class BasicCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemText: TextView = itemView.findViewById(R.id.basicText)
        val cardView: CardView = itemView.findViewById(R.id.basicCardView)
    }
}