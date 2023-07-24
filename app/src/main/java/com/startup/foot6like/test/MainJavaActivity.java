package com.startup.foot6like.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.startup.foot6like.R;

public class MainJavaActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    ViewPageAdapterJava viewPageAdapterJava;
    BottomNavigationView bottomNavigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        bottomNavigationView = findViewById(R.id.bottomNavigation);
//        viewPager2 = findViewById(R.id.viewPager);
//        viewPageAdapterJava = new ViewPageAdapterJava(this);
//        viewPager2.setAdapter(viewPageAdapterJava);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @SuppressLint("NonConstantResourceId")
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
//                int id = Item.getItemId();
//                switch (id) {
//                    case R.id.bottom_home:
//                        viewPager2.setCurrentItem(0);
//                        break;
//                    case R.id.bottom_support:
//                        viewPager2.setCurrentItem(1);
//                        break;
//                    case R.id.bottom_downloads:
//                        viewPager2.setCurrentItem(2);
//                        break;
//                }
//                return false;
//            }
//        });
//
//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                switch (position) {
//                    case 0:
//                        bottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
//                        break;
//                    case 1:
//                        bottomNavigationView.getMenu().findItem(R.id.bottom_support).setChecked(true);
//                        break;
//                    case 2:
//                        bottomNavigationView.getMenu().findItem(R.id.bottom_downloads).setChecked(true);
//                        break;
//                }
//                super.onPageSelected(position);
//            }
//        });

    }

}