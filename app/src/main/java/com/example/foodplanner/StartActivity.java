package com.example.foodplanner;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.auth.AuthActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class StartActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    Button button;
    @SuppressLint({"RestrictedApi","MissingInflatedId", "UseSupportActionBar"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         setActionBar(findViewById(R.id.materialToolbar));
        button = findViewById(R.id.button);
         if (savedInstanceState==null) {
             Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade);
             button.startAnimation(animation);
         }
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(StartActivity.this, AuthActivity.class));
             }
         });

    }
}