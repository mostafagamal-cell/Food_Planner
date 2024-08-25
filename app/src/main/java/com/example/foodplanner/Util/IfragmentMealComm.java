package com.example.foodplanner.Util;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meals;

public interface IfragmentMealComm {
     void onSucess(Meals meals);
     void onSucess(Categories categories);
     void onSucess(Ingradiants meals);
     void onSucess(Countries meals);
     void onError(String message);
}
