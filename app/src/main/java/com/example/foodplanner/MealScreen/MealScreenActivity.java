package com.example.foodplanner.MealScreen;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.ActivityMealScreenBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MealScreenActivity extends AppCompatActivity {

    public NavController mealScreenNavController;
    private ActivityMealScreenBinding binding;
    private int previousTabId = R.id.meal_Fragment;  // Set the initial tab to "All Meals"

    @Override
    public boolean onSupportNavigateUp() {
        return mealScreenNavController.navigateUp() || super.onSupportNavigateUp();
    }
    private boolean isNavigating = false;  // A flag to prevent recursion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using ViewBinding
        binding = ActivityMealScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up the NavHostFragment and NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(binding.fragmentContainerView2.getId());
        mealScreenNavController = navHostFragment.getNavController();

        // Set up the toolbar
        setSupportActionBar(binding.toolbar2);
        binding.toolbar2.setTitle(getString(R.string.All_Meals));
        NavigationUI.setupWithNavController(binding.toolbar2, mealScreenNavController);

        // Set up BottomNavigationView with NavController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, mealScreenNavController);
        mealScreenNavController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
           Log.d("MealScreenActivity", "Destination changed: " + navDestination.getLabel());
            if (!isNavigating) {
                int destinationId = navDestination.getId();
                if (destinationId == R.id.meal_Fragment) {
                    previousTabId = R.id.meal_Fragment;
                    binding.bottomNavigationView.setSelectedItemId(R.id.meal_Fragment);
                } else if (destinationId == R.id.favouriteScreen) {
                    previousTabId = R.id.favouriteScreen;
                    binding.bottomNavigationView.setSelectedItemId(R.id.favouriteScreen);
                }
            }
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int selectedItemId = item.getItemId();
            if (previousTabId != selectedItemId && !isNavigating) {
                isNavigating = true;  // Prevent recursion by setting the flag

                if (selectedItemId == R.id.meal_Fragment) {
                    previousTabId = R.id.meal_Fragment;
                    mealScreenNavController.navigate(R.id.meal_Fragment);
                } else if (selectedItemId == R.id.favouriteScreen) {
                    previousTabId = R.id.favouriteScreen;
                    mealScreenNavController.navigate(R.id.favouriteScreen);
                }

                isNavigating = false;  // Reset the flag after navigation completes
            }
            return true;
        });
    }
}
