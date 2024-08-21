package com.example.foodplanner.Util;

import android.view.View;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;

public interface MyClickListner {
    default void OnClick(Category name){}
    default void OnClick(Meal meal) {}
    default void OnClick(String meal,int x) {}
    default void OnClickDelte(Meal meal){}
    default void OnClickDelte(Plan meal){}

}
