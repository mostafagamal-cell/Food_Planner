package com.example.foodplanner.FavouriteScreen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Adapter.FavouriteAdpter;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Util.IfavouritePresenter;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FragmentFavouriteScreenBinding;

import java.util.ArrayList;

public class FavouriteScreen extends Fragment implements MyClickListner,IfavouritePresenter.COMM {
    FragmentFavouriteScreenBinding binding;
    FavouriteAdpter adapter;
    IfavouritePresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding = FragmentFavouriteScreenBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = FavouritePresenter.getInstance(this,requireActivity().getApplication());
        adapter=new FavouriteAdpter(this);
        presenter.readDatafromDB().observe(getViewLifecycleOwner(), meals -> {
            Meals meals1 =new Meals();
            meals1.meals=new ArrayList<>(meals);
            adapter.setcontect(meals1);
            binding.FaveortaieREC.setAdapter(adapter);
        });
        binding.asyncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user",null);
                presenter.saveOnClould(email,adapter.meals);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user",null);
                presenter.readOnClould(email);
            }
        });
    }

    @Override
    public void OnClick(Meal meal) {
        FavouriteScreenDirections.ActionFavouriteScreenToMealItemScreen action = FavouriteScreenDirections.actionFavouriteScreenToMealItemScreen(meal);
        NavHostFragment.findNavController(this).navigate(action);
    }
    @Override
    public void OnClickDelte(Meal meal) {
        adapter.remove(meal);
        presenter.deletFavourite(meal);
    }

    @Override
    public void onDataArrive(Meals meals) {
        Log.d("14488444", "onDataArrive: "+meals.meals.size());
        for (int m = 0; m < meals.meals.size(); m++) {
            presenter.saveOnDB(meals.meals.get(m));
        }
    }
}