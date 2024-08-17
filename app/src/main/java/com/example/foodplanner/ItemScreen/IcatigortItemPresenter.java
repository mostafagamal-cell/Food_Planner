package com.example.foodplanner.ItemScreen;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Util.ImainPresenter;

public interface IcatigortItemPresenter  extends ImainPresenter {
    void loadMeals(String category);
    void onDataArrived(Meals categories);
    interface IcatigortItemComm {
     void onDataArrived(Meals categories);
 }
}
