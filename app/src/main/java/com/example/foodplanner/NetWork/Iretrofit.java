package com.example.foodplanner.NetWork;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Iretrofit {
    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String name);
    @GET("search.php")
    Call<Meals> getMealByLetter(@Query("f") String letter);
    @GET("random.php")
    Call<Meals> getRandomMeal();
    @GET("categories.php")
    Call<Categories> getCategories();
    @GET("filter.php")
    Call<Meals> filterByCategory(@Query("c") String category);
    @GET("filter.php")
    Call<Meals> filterByArea(@Query("a") String Area);
    @GET("lookup.php")
    Call<Meals> getMealById(@Query("i") String id);
    @GET("filter.php")
    Call<Meals> filterByIngredient(@Query("i") String Ingredient);
    @GET("list.php")
    Call<Categories> getListOfCategories(@Query("c") String list);
    @GET("list.php")
    Call<Countries> getListOfArea(@Query("a") String list);
    @GET("list.php")
    Call<Ingradiants> getListOfIngredients(@Query("i") String list);

}
