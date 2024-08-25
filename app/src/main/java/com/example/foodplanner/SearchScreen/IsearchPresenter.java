package com.example.foodplanner.SearchScreen;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Util.ImainPresenter;

import java.util.ArrayList;

public interface IsearchPresenter  {
    void search(String query,String f1,String f2,String f3,String all);
    void Sucess(ArrayList<Meal> meals);
    void Fail(String message);
    void onIngArraied(ArrayList<String>d);
    void onAreaArraied(ArrayList<String>d);
    void onCatArraied(ArrayList<String>d);
}

