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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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
        ArrayList<String> a= getStartAndEndDate();
        return dao.getAllMealsPlanned(email,a.get(1),a.get(0));
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

    @Override
    public LiveData<Integer> checkinDatabase(String id) {
        return dao.countMealByNameAndCalories(id);
    }

    public ArrayList<String> getStartAndEndDate() {
        Calendar calendar = Calendar.getInstance();
        Date date= new Date();
        calendar.setTime(date);
        calendar.setTime(date);
        ArrayList<String> strings =new ArrayList<>();
        String o= calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        while (!Objects.requireNonNull(o).equalsIgnoreCase("Friday")){
            calendar.add(Calendar.DAY_OF_YEAR,1);
            o= calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.ENGLISH);
        }
        o= calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.ENGLISH);
        strings.add(sdf.format(calendar.getTime()));
        o= calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.ENGLISH);

        while (!Objects.requireNonNull(o).equalsIgnoreCase("Saturday")){
            calendar.add(Calendar.DAY_OF_YEAR,-1);
            o= calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.ENGLISH);
        }
        o= calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.ENGLISH);
        strings.add(sdf.format(calendar.getTime()));
        return strings;
    }
}
