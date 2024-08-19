package com.example.foodplanner.DatabaseRoom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.*;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;

import java.util.List;

@Dao
public interface MealDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
 void insertMeal(Meal meal);
@Query("SELECT * FROM FavouriteMeals Where email=:email")
LiveData<List<Meal>> getAllMeals(String email);
@Delete
void deleteMeal(Meal meal);
}
