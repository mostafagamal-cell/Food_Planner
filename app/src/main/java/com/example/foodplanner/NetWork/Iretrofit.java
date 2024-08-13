package com.example.foodplanner.NetWork;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Iretrofit {
    @GET("search.php?s={name}")
    Call<Meals> getMealByName(@Path("name") String name);
    @GET("search.php?f={letter}")
    Call<Meals> getMealByLetter(@Path("letter") String name);
    @GET("random.php")
    Call<Meals> getRandomMeal();
    @GET("categories.php")
    Call<Categories> getCategories();
    @GET("filter.php?c={category}")
    Call<Meals> filterByCategory(@Path("category") String category);
    @GET("filter.php?a={Area}")
    Call<Meals> filterByArea(@Path("Area") String Area);
    @GET("lookup.php?i={id}")
    Call<Meals> getMealById(@Path("id") String id);
    @GET("filter.php?i={Ingredient}")
    Call<Meals> filterByIngredient(@Path("Ingredient") String Ingredient);
    @GET("list.php?c={list}")
    Call<Meals> getListOfCategories(@Path("list") String list);
    @GET("list.php?a={list}")
    Call<Meals> getListOfArea(@Path("list") String list);
    @GET("list.php?i={list}")
    Call<Meals> getListOfIngredients(@Path("list") String list);

}
