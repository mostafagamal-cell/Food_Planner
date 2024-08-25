package com.example.foodplanner.DataSourse;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Model.PlannesMeal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;

import retrofit2.Callback;

public interface IremoteDataSourse {


    void getMealByname(String name, Callback<Meals> mealsCallback);

    void getMealByletter(String name, Callback<Meals> mealsCallback);

    void getRandommeal(Callback<Meals> mealsCallback);

    void getcategories(Callback<Categories> mealsCallback);

    void filterBycategory(String category, Callback<Meals> mealsCallback);

    void filterByarea(String Area, Callback<Meals> mealsCallback);

    void getMealByid(String id, Callback<Meals> mealsCallback);

    void filterByingredient(String Ingredient, Callback<Meals> mealsCallback);

    void getListOfcategories(String list, Callback<Categories> mealsCallback);

    void getListOfarea(Callback<Countries> mealsCallback);

    void getListOfingredients(String list, Callback<Ingradiants> mealsCallback);
    void readPlanedFromFireStore(String email, OnCompleteListener<DocumentSnapshot> onCompleteListener);
     void writePlanedFromFireStore(String email, PlannesMeal JsonData, OnCompleteListener<Void> completeListener);
    void writeFavouriteFromFireStore(String email, Meals JsonData,OnCompleteListener<Void> completeListener);
    public void readFavouriteFromFireStore(String email, OnCompleteListener<DocumentSnapshot> onCompleteListener);
    }
