package com.example.foodplanner.DataSourse;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.IngradintMeals;
import com.example.foodplanner.Model.Meals;

import retrofit2.Callback;

public interface IremoteDataSource {
    default Callback<Meals> mealsCallback(int type){return null;};
    default Callback<Categories> categoriesCallback(int type){return null;};
    default Callback<Ingradiants> ingradinatsCallback(int type){return null;};
    default Callback<Countries> countriesCallback(){return null;};

    void getMealByname(String name);
    void getMealByletter(String name);
    void getRandommeal();
    void getcategories();
    void filterBycategory( String category);
    void filterByarea(String Area);
    void getMealByid(String id);
    void filterByingredient(String Ingredient);
    void getListOfcategories(String list);
    void getListOfarea();
    void getListOfingredients(String list);
}
