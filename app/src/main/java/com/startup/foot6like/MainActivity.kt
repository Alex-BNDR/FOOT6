package com.startup.foot6like

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.startup.foot6like.ViewPageAdapter
import com.startup.foot6like.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var viewPager2: ViewPager2
    private lateinit var viewPageAdapter: ViewPageAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        bottomNavigationView = findViewById(R.id.bottomNavigation)
        viewPager2 = findViewById(R.id.viewPager)
        viewPageAdapter = ViewPageAdapter(this)
        viewPager2.adapter = viewPageAdapter
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            when (id) {
                R.id.bottom_home -> viewPager2.currentItem = 0
                R.id.bottom_blind -> viewPager2.currentItem = 1
                R.id.bottom_chat -> viewPager2.currentItem = 2
                R.id.bottom_support -> viewPager2.currentItem = 3

            }
            false
        }

        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottomNavigationView.menu.findItem(R.id.bottom_home).isChecked = true
                    1 -> bottomNavigationView.menu.findItem(R.id.bottom_blind).isChecked = true
                    2 -> bottomNavigationView.menu.findItem(R.id.bottom_chat).isChecked = true
                    3 -> bottomNavigationView.menu.findItem(R.id.bottom_support).isChecked = true
                }
                super.onPageSelected(position)
            }
        })

    }
}