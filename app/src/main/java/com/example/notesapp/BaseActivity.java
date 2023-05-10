package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
    }

    public BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            Fragment selectedFragment = null;
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    selectedFragment = new HomeFragment();
//                    break;
//                case R.id.navigation_dashboard:
//                    selectedFragment = new DashboardFragment();
//                    break;
//                case R.id.navigation_add:
//                    selectedFragment = new AddFragment();
//                    break;
//            }
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.navigation_dashboard) {
                selectedFragment = new DashboardFragment();
            } else if (itemId == R.id.navigation_add) {
                selectedFragment = new AddFragment();
            }

            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, selectedFragment).commit();
            return true;
        }
    };
}