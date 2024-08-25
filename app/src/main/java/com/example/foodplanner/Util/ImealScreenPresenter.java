package com.example.foodplanner.Util;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meals;

public interface ImealScreenPresenter  {
    void getListOfcategories();
    void getListOfarea();
    void getcatigorys();

    void getRandommeal();
    void getListOfingredients();
}
