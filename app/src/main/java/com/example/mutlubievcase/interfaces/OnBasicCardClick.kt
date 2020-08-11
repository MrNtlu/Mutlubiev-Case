package com.example.mutlubievcase.interfaces

import com.example.mutlubievcase.models.BasicCardData

interface OnBasicCardClick {
    fun onBasicCardSelected(position: Int, data: BasicCardData,previousSelectedPosition: Int?,previousSelected: BasicCardData?)
}