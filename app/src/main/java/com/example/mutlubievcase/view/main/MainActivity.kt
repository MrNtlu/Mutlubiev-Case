package com.example.mutlubievcase.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.mutlubievcase.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hostFragment=mainHostFragment as NavHostFragment
        navController=hostFragment.navController
        NavigationUI.setupWithNavController(mainBottomNav, navController)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            this.elevation = 3f
            this.setDisplayShowTitleEnabled(false)
        }

        setListeners()
    }

    private fun setListeners(){
        mainFab.setOnClickListener {
            navController.navigate(R.id.serviceOrderActivity,
                null,
                NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_up)
                    .setExitAnim(R.anim.slide_down)
                    .setPopEnterAnim(R.anim.slide_up)
                    .setPopExitAnim(R.anim.slide_down).build())
        }
    }
}