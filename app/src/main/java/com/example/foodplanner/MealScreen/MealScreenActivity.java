package com.example.foodplanner.MealScreen;

import static com.example.foodplanner.Util.InternetBroadcastReciver.booleanMutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.App;
import com.example.foodplanner.R;
import com.example.foodplanner.auth.AuthActivity;
import com.example.foodplanner.databinding.ActivityMealScreenBinding;

import java.util.Objects;

public class MealScreenActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getSharedPreferences("user",MODE_PRIVATE).edit().putString("user",null).apply();

        if (Objects.equals(App.Login_State.getValue(), App.Logged_in)){
            App.Login_State.setValue(App.not_Logged_in);
            Intent intent= new Intent(this, AuthActivity.class);
             startActivity(intent);
             finish();
        }else{
            App.naigateback=true;
            Intent intent= new Intent(this, AuthActivity.class);
            startActivity(intent);

        }



        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.topbar, menu);
        if (App.Login_State.getValue().equals(App.Logged_in)){

            menu.getItem(0).setTitle(getString(R.string.Logout));

        }else
        {
            menu.getItem(0).setTitle(getString(R.string.login));
        }
        return true;
    }
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
        binding.toolbar2.setTitle(getString(R.string.All_Meals));


        setSupportActionBar(binding.toolbar2);

        NavigationUI.setupWithNavController(binding.toolbar2, mealScreenNavController);
        // Set up BottomNavigationView with NavController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, mealScreenNavController);
        App.Login_State.observe(this, s -> {
            invalidateMenu();
        });

        booleanMutableLiveData.observe(this, aBoolean -> {
            if (!aBoolean &&
                    mealScreenNavController.getCurrentDestination() != null &&
                    mealScreenNavController.getCurrentDestination().getId() != R.id.planScreen &&
                    mealScreenNavController.getCurrentDestination().getId() != R.id.favouriteScreen &&
                    mealScreenNavController.getCurrentDestination().getId() != R.id.mealItemScreen &&
                    mealScreenNavController.getCurrentDestination().getId() != R.id.loginFragment2 &&
                    mealScreenNavController.getCurrentDestination().getId() != R.id.createAccountFragment) {
                hide();
            } else {
                show();
            }
        });
        mealScreenNavController
                .addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            if (navDestination.getId()==R.id.planScreen||navDestination.getId() == R.id.planScreen||navDestination.getId() == R.id.favouriteScreen||navDestination.getId() == R.id.mealItemScreen||navDestination.getId()==R.id.loginFragment2||navDestination.getId()==R.id.createAccountFragment){
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
                }else if (selectedItemId==R.id.planScreen){
                    mealScreenNavController.navigate(R.id.planScreen);
                }else if (selectedItemId==R.id.searchFrag){
                    mealScreenNavController.navigate(R.id.searchFrag);
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
