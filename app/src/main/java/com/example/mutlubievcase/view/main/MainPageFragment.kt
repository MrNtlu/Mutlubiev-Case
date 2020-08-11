package com.example.mutlubievcase.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mutlubievcase.R
import com.example.mutlubievcase.adapters.BasicViewPagerAdapter
import com.example.mutlubievcase.adapters.CampaignAdapter
import com.example.mutlubievcase.utils.Constants
import kotlinx.android.synthetic.main.fragment_main_page.*

class MainPageFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)

        setAdapters()
        setListeners()
    }

    private fun setAdapters(){
        threeStepViewPager.adapter = BasicViewPagerAdapter(Constants.threeStepList)
        threeStepCircleIndicator.setViewPager(threeStepViewPager)

        whyViewPager.adapter = BasicViewPagerAdapter(Constants.whyMutlubievList)
        whyCircleIndicator.setViewPager(whyViewPager)

        campaignRV.adapter = CampaignAdapter(Constants.campaignList)
        campaignRV.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun setListeners(){
        nanoCardView.setOnClickListener {
            navController.navigate(R.id.serviceOrderActivity)
        }

        nanoText.setOnClickListener {
            navController.navigate(R.id.serviceOrderActivity)
        }

        cleaningCardView.setOnClickListener {
            navController.navigate(R.id.serviceOrderActivity)
        }

        cleaningText.setOnClickListener {
            navController.navigate(R.id.serviceOrderActivity)
        }

        halfDayCardView.setOnClickListener {
            navController.navigate(R.id.serviceOrderActivity)
        }

        halfDayText.setOnClickListener {
            navController.navigate(R.id.serviceOrderActivity)
        }
    }
}