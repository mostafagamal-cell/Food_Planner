package com.example.foodplanner.Util;

import com.example.foodplanner.Model.Meals;

public interface IfavouriteFragment{
    void onSucces(Meals meals);
    void onSucces();

    void onError(String message);
}
