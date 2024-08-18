package com.example.foodplanner.Util;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meals;

public interface ImealScreenPresenter extends ImainPresenter  {
    void getListOfarea();
    void getcatigorys();
    void getRandommeal();
    void getListOfcategories();
    void getListOfingredients();
    interface Commncator  extends ImainPresenter {
        void onDataArrivedRandomaMeal(Meals meals);
        void onError(String message);
        void onDataArrivedCategories(Categories categories);
        void onDataArrivedIngredients(Ingradiants meals);
        void onDataArrivedCountry(Countries meals);

    }

}
