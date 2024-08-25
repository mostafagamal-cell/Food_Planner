package com.example.foodplanner.MealItem;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Model.Plan;

public interface ImealItemFragment  {
    void OnSucess(Meal meal);
    void OnError(String message);

}
