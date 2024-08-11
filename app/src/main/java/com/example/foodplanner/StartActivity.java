package com.example.foodplanner;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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

    }
}