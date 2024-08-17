package com.example.foodplanner.Util;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meals;

public interface IfragmentMealComm  extends ImainPresenter  {
     void onDataArrivedRandomaMeal(Meals meals);
     void onDataArrivedCategories(Categories categories);
     void onDataArrivedIngredients(Meals meals);
     void onDataArrivedCountry(Meals meals);
}
