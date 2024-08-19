package com.example.foodplanner.DataSourse;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DatabaseRoom.MealDao;
import com.example.foodplanner.DatabaseRoom.MyDataBase;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Repository.Irepo;

import java.util.List;

public class LocalDataSourse implements IlocalDataSource {
    MealDao dao;
    Application application;
    public LocalDataSourse(Application context)
   {
    dao=MyDataBase.getInstance(context).mealDao();
    this.application=context;
   }
    @Override
    public LiveData<List<Meal>> getFavourites() {
       String email= application.getSharedPreferences("user", Context.MODE_PRIVATE).getString("user",null);
       Log.e("asddddddddddddddddddddddd",email);
       return dao.getAllMeals(email);
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
