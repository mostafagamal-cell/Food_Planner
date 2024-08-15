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
    NavController navController;
    ActivityMealScreenBinding binding;
    int prv=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding= ActivityMealScreenBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());
         NavHostFragment navHost= (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.fragmentContainerView2.getId());
         navController =navHost.getNavController();
         NavigationUI.setupWithNavController(binding.bottomNavigationView,navController);
         binding.bottomNavigationView.setOnClickListener(new NavigationBarItemView.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });
         binding.bottomNavigationView.setOnItemSelectedListener(item -> {
             if (item.getTitle().equals(getString(R.string.All_Meals))&&prv!=0){
                 Log.i("eeeeeexxxxxxxeeeeeeee",item.getTitle().toString());
                 navController.navigate(R.id.meal_Fragment);
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