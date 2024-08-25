package com.example.foodplanner.SearchScreen;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meal;

import java.util.ArrayList;
import java.util.Vector;

public interface IsearchFragment  {
    void Sucess(Vector<Meal> list);
    void onSucess(Vector<String> ing);
    void onsucess(Vector<String> area);
    void onsUcess(Vector<String> cats);
    void Fail(String meassage);
}
