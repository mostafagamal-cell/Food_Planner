package com.example.foodplanner.MealItem;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Util.ImainPresenter;

public interface ImealItemPreseter extends ImainPresenter {

    void loadMealById(Meal meal);
    void dataArrived(Meal meal);
    interface ImealScreenComm {
        void dataArrived(Meal meal);
    }
}
