package com.example.foodplanner.Util;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;

import retrofit2.Callback;

public interface IremoteDataSource {
    Callback<Meals> mealsCallback();
    Callback<Categories> categoriesCallback();
    void getMealByname(String name);
    void getMealByletter(String name);
    void getRandommeal();
    void getcategories();
    void filterBycategory( String category);
    void filterByarea(String Area);
    void getMealByid(String id);
    void filterByingredient(String Ingredient);
    void getListOfcategories(String list);
    void getListOfarea(String list);
    void getListOfingredients(String list);
}
