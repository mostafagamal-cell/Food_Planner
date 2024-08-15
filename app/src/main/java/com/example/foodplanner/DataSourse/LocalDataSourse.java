package com.example.foodplanner.DataSourse;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DatabaseRoom.MealDao;
import com.example.foodplanner.DatabaseRoom.MyDataBase;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Repository.Irepo;

import java.util.List;

public class LocalDataSourse implements IlocalDataSource {
    MealDao dao;
    public LocalDataSourse(Application context)
   {
    dao=MyDataBase.getInstance(context).mealDao();
   }
    @Override
    public LiveData<List<Meal>> getFavourites() {
       return dao.getAllMeals();
    }

    @Override
    public void insertFavourites(Meal meal) {
       new Thread(new Runnable() {
           @Override
           public void run() {
               dao.insertMeal(meal);
           }
       }).start();
    }

    @Override
    public void deleteFavourites(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.deleteMeal(meal);
            }
        }).start();

    }
}
