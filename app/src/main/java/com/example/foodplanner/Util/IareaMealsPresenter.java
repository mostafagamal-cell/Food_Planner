package com.example.foodplanner.Util;

import com.example.foodplanner.Model.Meals;

public interface IareaMealsPresenter extends  ImainPresenter {
    void loadMeals(String category);
    void onDataArrived(Meals categories);
    interface IareaMealsPresenterComm {
        void onDataArrived(Meals categories);
    }
}