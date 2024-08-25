package com.example.foodplanner.Util;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;

import java.util.List;

public interface IfavouritePresenter extends ImainPresenter{
         void deletFavourite(Meal meal);
         void saveOnClould(String email,Meals meal);
         void readOnClould(String email);
         void saveOnDB(Meal meal);
         LiveData<List<Meal>> readDatafromDB();
         void dataArriveFromCloud(Meals meals);
}
