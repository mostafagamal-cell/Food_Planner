package com.example.foodplanner.MealPlane;

import com.example.foodplanner.Model.Plan;

import java.util.List;

public interface ImealPlannerFrag{
     void onSucces(List<Plan> meal);
      void onSucces();
      void onError(String message);

}
