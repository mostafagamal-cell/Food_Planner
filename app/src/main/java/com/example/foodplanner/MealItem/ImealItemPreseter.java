package com.example.foodplanner.MealItem;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Util.ImainPresenter;

public interface ImealItemPreseter  {
    void instertMeal(Meal meal);
    void loadMealById(Meal meal);
    void saveMeal(Meal meal);
    void savePlan(Plan plan);
    LiveData<Integer>checkinDatabase(String id);
}

