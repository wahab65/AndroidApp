package com.example.deelio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    //Declare all elements that will need probable coding
    TextView tvGoToRegisterActivity;
    ImageView ivAnimatedLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().hide();

        //list all element bindings here
        tvGoToRegisterActivity= (TextView)findViewById(R.id.loginNregisterSwitcher);
        ivAnimatedLogo = (ImageView) findViewById(R.id.imageViewAnimLogo) ;



        tvGoToRegisterActivity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ivAnimatedLogo.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable spinnerAnim = (AnimationDrawable) ivAnimatedLogo.getDrawable();
                    spinnerAnim.start();
            }
        });

    }



}