package com.example.foodplanner.DataSourse;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;

import java.util.List;

public interface IlocalDataSource {
    LiveData<List<Meal>> getFavourites();
    LiveData<List<Plan>> getPlanned(String email);
    void insertFavourites(Meal meal);
    void deleteFavourites(Meal meal);
    void insertPlanned(Plan meal);
    void deletePlanned(Plan meal);
    LiveData<Integer> checkinDatabase(String id);
}
