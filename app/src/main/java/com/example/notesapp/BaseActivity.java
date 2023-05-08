package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);
    }

    public BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment unFragment = null;
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    unFragment = new HomeFragment();
//                    break;
//                case R.id.navigation_dashboard:
//                    unFragment = new DashboardFragment();
//                    break;
//                case R.id.navigation_add:
//                    unFragment = new AddFragment();
//                    break;
//            }
            Bundle bundle = new Bundle();

            unFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentplaceholder, unFragment).commit();
            return true;
        }
    };
}