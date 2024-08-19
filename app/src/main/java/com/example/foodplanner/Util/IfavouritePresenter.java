package com.example.foodplanner.Util;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;

import java.util.List;

public interface IfavouritePresenter extends ImainPresenter{
         void deletFavourite(Meal meal);
         void saveOnClould(String email,Meals meal);
         void readOnClould(String email);
         LiveData<List<Meal>> readDatafromDB();
         List<Meal> dataArriveFromCloud(Meals meals);

}
