package com.example.mutlubievcase.view.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.mutlubievcase.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_service_order.*

class ServiceOrderActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onBackPressed() {
        this.finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_order)

        val hostFragment=serviceHostFragment as NavHostFragment
        navController=hostFragment.navController
    }
}