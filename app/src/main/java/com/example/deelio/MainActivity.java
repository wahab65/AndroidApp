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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        mFirebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_main);

        //bottomNav reference
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        //bind a navListener
        bottomNav.setOnNavigationItemSelectedListener( navListener);
        //default navigation set to HomeFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, new HomeFragment()).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser != null){
            //there is user logged in

        }else{
            //no user logged in
            startActivity(new Intent(this, LoginActivity.class));

        }
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