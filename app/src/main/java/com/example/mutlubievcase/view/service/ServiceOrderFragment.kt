package com.example.mutlubievcase.view.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mutlubievcase.R
import kotlinx.android.synthetic.main.fragment_service_order.*

class ServiceOrderFragment : Fragment() {
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_service_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        setListeners()

    }

    private fun setListeners(){
        closeButton.setOnClickListener {
            activity?.finish()
        }

        homeCleaningLayout.setOnClickListener {
            navController.navigate(R.id.action_serviceOrderFragment_to_orderSelectionFragment)
        }

        halfDayLayout.setOnClickListener {
            navController.navigate(R.id.action_serviceOrderFragment_to_orderSelectionFragment)
        }

        nanoLayout.setOnClickListener {
            navController.navigate(R.id.action_serviceOrderFragment_to_orderSelectionFragment)
        }
    }
}