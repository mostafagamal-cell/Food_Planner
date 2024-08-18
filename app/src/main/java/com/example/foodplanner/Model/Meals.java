package com.example.foodplanner.Model;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class Meals {
    public ArrayList<Meal> meals;

    @NonNull
    @Override
    public String toString() {
        StringBuilder data= new StringBuilder();
        for(int i=0;i<meals.size();i++){
            data.append(" ").append(meals.get(i).idMeal).append(" || ");
        }
        return data.toString();
    }
}
