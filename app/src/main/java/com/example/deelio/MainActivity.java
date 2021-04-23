package com.example.deelio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.deelio.Fragments.HomeFragment;
import com.example.deelio.Fragments.PostFragment;
import com.example.deelio.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //bottomNav reference
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        //bind a navListener
        bottomNav.setOnNavigationItemSelectedListener( navListener);
        //default navigation set to HomeFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, new HomeFragment()).commit();

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        final Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //setup navListener
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.miAddPost:
                    selectedFragment = new PostFragment();
                    break;
                case R.id.miHome:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.miProfile:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer, selectedFragment)
                    .commit();
            return true;
        }
    };





}