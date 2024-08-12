package com.example.foodplanner.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.MaleFragment;
import com.example.foodplanner.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
   Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);
        FirebaseAuth auth =FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            startActivity(new Intent(AuthActivity.this, MaleFragment.class));
            finish();
        }
    }
}