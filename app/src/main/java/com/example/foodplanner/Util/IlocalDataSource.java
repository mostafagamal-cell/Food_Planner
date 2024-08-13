package com.example.foodplanner.Util;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;

import java.util.List;

public interface IlocalDataSource {
    LiveData<List<Meal>> getFavourites();
    void insertFavourites(Meal meal);
    void deleteFavourites(Meal meal);
}
