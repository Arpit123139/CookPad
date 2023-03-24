package com.example.foodreciepie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

import androidx.navigation.fragment.findNavController
import com.example.Category.CategoryFragment
import com.example.Favourite.FavouriteFragment
import com.example.home.HomeFragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.home -> {
//                   supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, HomeFragment())
//                        .commit();
//                }
//                R.id.favourite-> {
//                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, FavouriteFragment())
//                        .commit();
//                }
//                R.id.category -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, CategoryFragment())
//                        .commit();
//                }
//            }
//            true
//        }

    }
}
