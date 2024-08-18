package com.example.foodplanner.Util;

import com.example.foodplanner.Model.Meals;

public interface IcatigortItemPresenter  extends ImainPresenter {
    void loadMeals(String category);
    void onDataArrived(Meals categories);
    interface IcatigortItemComm {
     void onDataArrived(Meals categories);
 }
}
