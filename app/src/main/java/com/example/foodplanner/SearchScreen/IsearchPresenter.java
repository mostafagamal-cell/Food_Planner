package com.example.foodplanner.SearchScreen;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Util.ImainPresenter;

import java.util.ArrayList;
import java.util.Vector;

public interface IsearchPresenter  {
    void search(String query,String f1,String f2,String f3,String all);
    void Sucess(Vector<Meal> meals);
    void Fail(String message);
    void onIngArraied(Vector<String>d);
    void onAreaArraied(Vector<String>d);
    void onCatArraied(Vector<String> d);
}

