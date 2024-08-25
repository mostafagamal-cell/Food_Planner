package com.example.foodplanner.Repository;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Model.PlannesMeal;
import com.example.foodplanner.SearchScreen.IsearchFragment;
import com.example.foodplanner.SearchScreen.IsearchPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

import retrofit2.Callback;

public interface ImyRepository {
    LiveData<List<Meal>> getFavourites();

    void insertFavourites(Meal meal);

    void deleteFavourites(Meal meal);

    void insertPlanned(Plan meal);

    void deletePlanned(Plan meal);

    LiveData<Integer> checkinDatabase(String id);

     void  getMealByletter(String name, Callback<Meals> callback);

    void getRandommeal(Callback<Meals> callback);

    void getListOfcategories(Callback<Categories> callback);

    void getListOfingredients(Callback<Ingradiants> callback);

    void getcategories(Callback<Categories> callback);

    void filterBycategory(String category, Callback<Meals> callback);

    void filterByarea(String Area, Callback<Meals> callback);

    void getMealByid(String id, Callback<Meals> mealsCallback);

    void filterByingredient(String Ingredient, Callback<Meals> callback);

    void getListOfcategories(String list, Callback<Categories> callback);

    void getListOfarea(Callback<Countries> callback);

    void getcatigorys(Callback<Categories> callback);

    void getListOfingredients(String list, Callback<Ingradiants> callback);

    void search(IsearchPresenter presenter, String query, String f1, String f2, String f3, String all);

    LiveData<List<Plan>> getPlanned(String email);

    void readFavouriteFromFireStore(String email, OnCompleteListener<DocumentSnapshot> completeListener);

    void writePlanedFromFireStore(String email, PlannesMeal JsonData, OnCompleteListener<Void> completeListener);
    void readPlanedFromFireStore(String email, OnCompleteListener<DocumentSnapshot> completeListener);
    void writeFavouriteFromFireStore(String email, Meals JsonData, OnCompleteListener<Void> completeListener);
}
