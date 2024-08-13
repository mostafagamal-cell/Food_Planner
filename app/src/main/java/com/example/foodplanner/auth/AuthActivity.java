package com.example.foodplanner.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.MaleFragment;
import com.example.foodplanner.R;
import com.example.foodplanner.databinding.AuthActivityBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
    AuthActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=AuthActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseApp.initializeApp(this);
        FirebaseAuth auth =FirebaseAuth.getInstance();
        SharedPreferences preferences= this.getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        if (preferences.getString("user",null)!=null){
            startActivity(new Intent(AuthActivity.this, MaleFragment.class));
            finish();
        }
        NavHostFragment navHost= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController =navHost.getNavController();

        NavigationUI.setupWithNavController(binding.toolbar,navController);
    }
}