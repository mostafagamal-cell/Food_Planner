package com.example.foodplanner.MealScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Presenters.MealScreenPresenter;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.ImealScreenPresenter;
import com.example.foodplanner.Util.Irepo;
import com.example.foodplanner.databinding.FragmentMealBinding;


public class Meal_Fragment extends Fragment implements ImealScreenPresenter {
    FragmentMealBinding binding;
    MealScreenPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding=FragmentMealBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=MealScreenPresenter.getinstance(this,this.requireActivity().getApplication());
        presenter.getRandommeal();
    }

    @Override
    public void onDataArrivedRandomaMeal(Meals meals) {
        Log.i("dddddddddd",meals.meals.get(0).strMealThumb);
        Glide.with(this)
                .load(meals.meals.get(0).strMealThumb)
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background) .error(R.drawable.ic_launcher_foreground).override(100, 100))
                .into(binding.myImage);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onDataArrivedCategories(Categories categories) {

    }

    @Override
    public void onDataArrivedIngredients(Meals meals) {

    }

    @Override
    public void onDataArrivedCountry(Meals meals) {

    }
}