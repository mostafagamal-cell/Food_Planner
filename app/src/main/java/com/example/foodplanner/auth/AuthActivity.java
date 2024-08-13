package com.example.foodplanner.auth;

import android.content.Intent;
import android.content.SharedPreferences;
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
        setContentView(R.layout.auth_activity);
        FirebaseApp.initializeApp(this);
        FirebaseAuth auth =FirebaseAuth.getInstance();
        SharedPreferences preferences= this.getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        if (preferences.getString("user",null)!=null){
            startActivity(new Intent(AuthActivity.this, MaleFragment.class));
            finish();
        }
    }
}