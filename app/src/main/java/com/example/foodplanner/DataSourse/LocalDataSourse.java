package com.example.foodplanner.DataSourse;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DatabaseRoom.MealDao;
import com.example.foodplanner.DatabaseRoom.MyDataBase;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Util.IlocalDataSource;

import java.util.List;

public class LocalDataSourse implements IlocalDataSource {
    MealDao dao;
    private LocalDataSourse(Application context)
   {
    dao=MyDataBase.getInstance(context).mealDao();
   }
    @Override
    public LiveData<List<Meal>> getFavourites() {
       return dao.getAllMeals();
    }

    @Override
    public void insertFavourites(Meal meal) {
       dao.insertMeal(meal);
    }

    @Override
    public void deleteFavourites(Meal meal) {
       dao.deleteMeal(meal);
    }
}
