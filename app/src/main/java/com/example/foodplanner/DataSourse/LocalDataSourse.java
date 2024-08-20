package com.example.foodplanner.DataSourse;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DatabaseRoom.MealDao;
import com.example.foodplanner.DatabaseRoom.MyDataBase;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Repository.Irepo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
       return dao.getAllMeals(email);
    }

    @Override
    public LiveData<List<Plan>> getPlanned(String email) {
        String startOfWeek =getStartOfWeek();
        String endOfWeek = getEndOfWeek();
        return dao.getAllMealsPlanned(email,startOfWeek,endOfWeek);
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

    @Override
    public void insertPlanned(Plan meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insertPlan(meal);
            }
        }).start();
    }

    @Override
    public void deletePlanned(Plan meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.deletePlane(meal);
            }
        }).start();
    }
    public static String getStartOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date start = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(start);
    }

    public static String getEndOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date end = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(end);
    }
}
