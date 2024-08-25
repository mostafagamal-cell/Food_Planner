package com.example.foodplanner.SearchScreen;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meal;

import java.util.ArrayList;

public interface IsearchFragment  {
    void Sucess(ArrayList<Meal> list);
    void onSucess(ArrayList<String> ing);
    void onsucess(ArrayList<String> area);
    void onsUcess(ArrayList<String> cats);
    void Fail(String meassage);
}
