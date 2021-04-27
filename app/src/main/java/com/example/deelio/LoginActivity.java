package com.example.deelio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    //Declare all elements that will need probable coding
    TextView tvGoToRegisterActivity;
    ImageView ivAnimatedLogo;
    Button loginButton;
    ProgressBar loginprogressBar;
    FirebaseAuth fAuth;
    EditText loginEmail, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        this.getSupportActionBar().hide();

        //list all element bindings here
        tvGoToRegisterActivity= (TextView)findViewById(R.id.loginNregisterSwitcher);
        ivAnimatedLogo = (ImageView) findViewById(R.id.imageViewAnimLogo) ;
        loginButton = findViewById(R.id.loginButton);
        loginprogressBar = findViewById(R.id.loginprogressBar);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
//        if(fAuth !=null){
//            final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }


        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                //to check if user email is empty
                if(TextUtils.isEmpty(email)){
                    loginEmail.setError("Email is Required!");
                    return;
                }
                //to check if password is empty
                if(TextUtils.isEmpty(password)){
                    loginPassword.setError("Password is Required!");
                    return;
                }
                //to check if password has less than 6 characters
                if(password.length() < 6){
                    loginPassword.setError("Password must have 6 or more characters");
                    return;
                }

                loginprogressBar.setVisibility(View.VISIBLE);

                //authenticate the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "User Successfully Logged In", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "Logged In Successfully");
                            final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            loginprogressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Error: !", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Failed to Login User", task.getException());
                        }
                    }
                });

            }
        });

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