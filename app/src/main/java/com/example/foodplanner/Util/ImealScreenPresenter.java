package com.example.foodplanner.Util;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meals;

public interface ImealScreenPresenter {
    void onDataArrivedRandomaMeal(Meals meals);
    void onError(String message);
    void onDataArrivedCategories(Categories categories);
    void onDataArrivedIngredients(Meals meals);
    void onDataArrivedCountry(Meals meals);
}
