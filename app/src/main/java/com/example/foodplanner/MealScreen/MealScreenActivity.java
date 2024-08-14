package com.example.foodplanner.MealScreen;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.ActivityMealScreenBinding;

public class MealScreenActivity extends AppCompatActivity {
    NavController navController;
    ActivityMealScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding= ActivityMealScreenBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_meal_screen);
        NavHostFragment navHost= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
         navController =navHost.getNavController();}
}