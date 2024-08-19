package com.example.foodplanner.MealScreen;

import static com.example.foodplanner.Util.InternetBroadcastReciver.booleanMutableLiveData;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.ActivityMealScreenBinding;

public class MealScreenActivity extends AppCompatActivity {
    boolean started=false;
    public NavController mealScreenNavController;
    private ActivityMealScreenBinding binding;
    private int previousTabId = R.id.meal_Fragment;  // Set the initial tab to "All Meals"
    private boolean isNavigating = false;  // A flag to prevent recursion

    @Override
    public boolean onSupportNavigateUp() {
        return mealScreenNavController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        booleanMutableLiveData.observe(this, aBoolean -> {
            if (!aBoolean){
               hide();
            }else{
             show();
            }
        });
        mealScreenNavController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            if (navDestination.getId() == R.id.favouriteScreen||navDestination.getId() == R.id.mealItemScreen){
                show();
            }else {
                if (!booleanMutableLiveData.getValue()){
                    hide();
                }else{
                    show();
                }
            }

        });
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int selectedItemId = item.getItemId();
            if (mealScreenNavController.getCurrentDestination().getId() == selectedItemId) {
                return false;
            }
                if (selectedItemId == R.id.meal_Fragment) {
                    mealScreenNavController.popBackStack(mealScreenNavController.getGraph().getStartDestinationId(), false);
                } else if (selectedItemId == R.id.favouriteScreen) {
                    mealScreenNavController.navigate(R.id.favouriteScreen);
                }
            return true;
        });
    }

    // Helper method to clear all backstack and replace with the new fragment
    private NavOptions getReplaceAllNavOptions() {
        return new NavOptions.Builder()
                .setPopUpTo(mealScreenNavController.getGraph().getStartDestinationId(), true)  // Pop up to the root of the backstack, inclusive
                .build();
    }
    void show( ){
        binding.fragmentContainerView2.setVisibility(View.VISIBLE);
        binding.imageView2.setVisibility(View.INVISIBLE);
    }
    void hide(){
        binding.fragmentContainerView2.setVisibility(View.INVISIBLE);
        binding.imageView2.setVisibility(View.VISIBLE);
    }
}
