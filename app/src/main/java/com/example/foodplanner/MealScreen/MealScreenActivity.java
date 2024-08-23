package com.example.foodplanner.MealScreen;

import static com.example.foodplanner.Util.InternetBroadcastReciver.booleanMutableLiveData;
import static com.example.foodplanner.Util.InternetBroadcastReciver.ss;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
        getSharedPreferences("user", MODE_PRIVATE).edit().putString("user", null).apply();

        if (Objects.equals(App.Login_State.getValue(), App.Logged_in)) {
            App.Login_State.setValue(App.not_Logged_in);
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
            finish();
        } else {
            App.naigateback = true;
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.topbar, menu);
        if (App.Login_State.getValue().equals(App.Logged_in)) {

            menu.getItem(0).setTitle(getString(R.string.Logout));

        } else {
            menu.getItem(0).setTitle(getString(R.string.login));
        }
        return true;
    }

    boolean started = false;
    public NavController mealScreenNavController;
    private ActivityMealScreenBinding binding;
    private int previousTabId = R.id.meal_Fragment;  // Set the initial tab to "All Meals"
    private boolean isNavigating = false;  // A flag to prevent recursion


    @Override
    protected void onCreate(Bundle savedInstanceState) {

            booleanMutableLiveData.observe(this, aBoolean -> {
                if (!ss) {
                    if (aBoolean)
                    Toast.makeText(this, this.getString(R.string.connected), Toast.LENGTH_SHORT).show();
                    else
                    Toast.makeText(this, this.getString(R.string.notconnected), Toast.LENGTH_SHORT).show();
                }
                });
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

        App.Login_State.observe(this, s -> invalidateMenu());

        booleanMutableLiveData.observe(this, aBoolean -> updateUIVisibility());

        mealScreenNavController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            updateUIVisibility();
            updateBottomNavigation(navDestination);
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int selectedItemId = item.getItemId();
            if (mealScreenNavController.getCurrentDestination().getId() == selectedItemId) {
                return false;
            }
            navigateTo(selectedItemId);
            return true;
        });
    }

    private void updateUIVisibility() {
        if (!booleanMutableLiveData.getValue() &&
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
    }

    private void updateBottomNavigation(@NonNull NavDestination navDestination) {
        int destinationId = navDestination.getId();
        if (destinationId == R.id.planScreen) {
            binding.bottomNavigationView.getMenu().getItem(3).setChecked(true);
        } else if (destinationId == R.id.favouriteScreen) {
            binding.bottomNavigationView.getMenu().getItem(2).setChecked(true);
        } else if (destinationId == R.id.searchFrag) {
            binding.bottomNavigationView.getMenu().getItem(1).setChecked(true);
        } else if (destinationId == R.id.meal_Fragment2) {
            binding.bottomNavigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    private void navigateTo(int itemId) {
        if (itemId == R.id.meal_Fragment) {
            mealScreenNavController.popBackStack(mealScreenNavController.getGraph().getStartDestinationId(), false);
        } else if (itemId == R.id.favouriteScreen) {
            mealScreenNavController.navigate(R.id.favouriteScreen);
        } else if (itemId == R.id.planScreen) {
            mealScreenNavController.navigate(R.id.planScreen);
        } else if (itemId == R.id.searchFrag) {
            mealScreenNavController.navigate(R.id.searchFrag);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        boolean navigatedUp = mealScreenNavController.navigateUp();
        if (!navigatedUp) {
            onBackPressed();
        }
        return navigatedUp || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (!mealScreenNavController.popBackStack()) {
            super.onBackPressed();
        }
        NavDestination currentDestination = mealScreenNavController.getCurrentDestination();
        if (currentDestination != null) {
            updateBottomNavigation(currentDestination);
        }
    }

    void show() {
        binding.fragmentContainerView2.setVisibility(View.VISIBLE);
        binding.imageView2.setVisibility(View.INVISIBLE);
    }

    void hide() {
        binding.fragmentContainerView2.setVisibility(View.INVISIBLE);
        binding.imageView2.setVisibility(View.VISIBLE);
    }
}