package com.example.foodplanner.DatabaseRoom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.*;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Model.Plan;

import java.util.List;

@Dao
public interface MealDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
 void insertMeal(Meal meal);
@Query("SELECT * FROM FavouriteMeals Where email=:email")
LiveData<List<Meal>> getAllMeals(String email);
@Delete
void deleteMeal(Meal meal);
@Query("SELECT * FROM Plans WHERE email = :email AND Day BETWEEN :startDate AND :endDate")
LiveData<List<Plan>> getAllMealsPlanned(String email,String startDate ,String endDate);
 @Insert(onConflict = OnConflictStrategy.REPLACE)
 void insertPlan(Plan plan);
 @Delete
 void deletePlane(Plan plan);
 @Query("SELECT COUNT(*) FROM FavouriteMeals WHERE idMeal = :id")
 LiveData<Integer> countMealByNameAndCalories(String id);
}
