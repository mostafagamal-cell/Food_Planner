package com.example.foodplanner.MealPlane;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Util.ImainPresenter;

import java.util.ArrayList;
import java.util.List;

public interface ImealPlannerPresenter extends ImainPresenter {
    LiveData<List<Plan>> getPlans(String email);
    void insertPlan(Plan plan);

    interface Comm {
        void onDataArrived(List<Plan>meal);
    }

}
