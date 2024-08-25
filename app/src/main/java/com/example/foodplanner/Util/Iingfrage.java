package com.example.foodplanner.Util;

import com.example.foodplanner.Model.Meals;

public interface Iingfrage {
    void onSuccess(Meals categories);
    void onError(String message);
}
