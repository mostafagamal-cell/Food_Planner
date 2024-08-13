package com.example.foodplanner.DatabaseRoom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.*;

import com.example.foodplanner.Model.Meal;

import java.util.List;

@Dao
public interface MealDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
 void insertMeal(Meal meal);
@Query("SELECT * FROM FavouriteMeals")
LiveData<List<Meal>> getAllMeals();
@Delete
void deleteMeal(Meal meal);
}
