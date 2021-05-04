package com.example.deelio;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.deelio.Fragments.HomeFragment;
import com.example.deelio.Fragments.PostFragment;
import com.example.deelio.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Deelio);
        this.getSupportActionBar().hide();

        mFirebaseAuth = FirebaseAuth.getInstance();


        setContentView(R.layout.activity_main);

        //bottomNav reference
        //BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        //bind a navListener
        //bottomNav.setOnNavigationItemSelectedListener( navListener);
        //default navigation set to HomeFragment
        //getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, new HomeFragment()).commit();


        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.browse_tab));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.add_tab));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.profile_tab));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new PostFragment();
                        break;
                    case 3:
                        fragment = new ProfileFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });
        bottomNavigation.setCount(1, "10");
        bottomNavigation.show(1,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(), "You Clicked " +item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(MainActivity.this, "You Reselected " +item.getId()
                      //  , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainer, fragment)
                .commit();
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
    /*private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
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
    };*/





}