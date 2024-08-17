package com.example.foodplanner.Util;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Categories;
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
        void onDataArrivedIngredients(Meals meals);
        void onDataArrivedCountry(Meals meals);

    }

}
