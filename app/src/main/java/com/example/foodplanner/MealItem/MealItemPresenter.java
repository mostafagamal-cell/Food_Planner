package com.example.foodplanner.MealItem;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Repository.MyRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealItemPresenter implements ImealItemPreseter, Callback<Meals> {
    public static final String TAG = "MealItemPresenter";
    ImealItemFragment fragment;
    private static MealItemPresenter instance;
    private MyRepository repository;
    public MealItemPresenter(ImealItemFragment f,MyRepository repository){
        this.fragment=f;
        this.repository=repository;
    }



    @Override
    public void instertMeal(Meal meal) {
        repository.insertFavourites(meal);
    }


    @Override
    public void loadMealById(Meal meal) {
     repository.getMealByid(meal.idMeal,this);
    }

    @Override
    public void saveMeal(Meal meal) {
        repository.insertFavourites(meal);
    }

    @Override
    public void savePlan(Plan plan) {
        repository.insertPlanned(plan);

    }

    @Override
    public LiveData<Integer> checkinDatabase(String id) {
        return repository.checkinDatabase(id);
    }

    @Override
    public void onResponse(Call<Meals> call, Response<Meals> response) {
        fragment.OnSucess(response.body().meals.get(0));
    }

    @Override
    public void onFailure(Call<Meals> call, Throwable throwable) {
    fragment.OnError(throwable.getMessage());
    }
}
