package com.example.foodplanner.Util;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meals;

public interface IfragmentMealComm  extends ImainPresenter  {
     void onDataArrivedRandomaMeal(Meals meals);
     void onDataArrivedCategories(Categories categories);
     void onDataArrivedIngredients(Ingradiants meals);
     void onDataArrivedCountry(Countries meals);
}
