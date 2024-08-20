package com.example.foodplanner.DatabaseRoom;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;

@Database(entities = {Meal.class, Plan.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
   public static MyDataBase INSTANCE=null;
    public abstract MealDao mealDao();
    public static synchronized MyDataBase getInstance(Application application){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(application.getApplicationContext(),MyDataBase.class,"my_db").build();
    }
    return  INSTANCE;
    }
}
