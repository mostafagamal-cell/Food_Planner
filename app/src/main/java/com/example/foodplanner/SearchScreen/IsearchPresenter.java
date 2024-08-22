package com.example.foodplanner.SearchScreen;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Util.ImainPresenter;

import java.util.ArrayList;

public interface IsearchPresenter extends ImainPresenter {
    void search();
    interface Comm{
        void dataArrivedSearch(ArrayList<Meal> result);
    }
}
