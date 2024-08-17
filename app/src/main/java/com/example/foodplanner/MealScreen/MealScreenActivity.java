package com.example.foodplanner.MealScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
import com.google.android.material.navigation.NavigationBarItemView;

public class MealScreenActivity extends AppCompatActivity {
   public  NavController MealScreenActivitynavController;
    ActivityMealScreenBinding binding;
    int prv=0;

    @Override
    public boolean onSupportNavigateUp() {
        Log.i("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","eeeeeeeeeeeeeeeeeeaa");
        return MealScreenActivitynavController.navigateUp()||super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding= ActivityMealScreenBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());
         NavHostFragment navHost= (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.fragmentContainerView2.getId());
         setSupportActionBar(binding.toolbar2);
        MealScreenActivitynavController =navHost.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigationView,MealScreenActivitynavController);
        NavigationUI.setupWithNavController(binding.toolbar2,MealScreenActivitynavController);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
             if (item.getTitle().equals(getString(R.string.All_Meals))&&prv!=0){
                 Log.i("eeeeeexxxxxxxeeeeeeee",item.getTitle().toString());
                 MealScreenActivitynavController.navigate(R.id.meal_Fragment);
                 prv=0;
             }
             else if (item.getTitle().equals(getString(R.string.Search))){
              //   navController.navigate(R.id.search_Fragment);
                 prv=1;
             }
             else if (item.getTitle().equals(getString(R.string.Favourite_Meals))){
                // navController.navigate(R.id.favourite_Fragment);
                 prv=2;

             }
             else if (item.getTitle().equals(getString(R.string.Meals_of_Week))){
                 // navController.navigate(R.id.week_Fragment);
                 prv=3;

             }
             return true;
         });

    }
}