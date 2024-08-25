package com.example.foodplanner.SearchScreen;

import static com.example.foodplanner.Repository.MyRepository.all_meals;

import android.app.Application;
import android.util.Log;

import com.example.foodplanner.App;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Repository.MyRepository;

import java.util.ArrayList;
import java.util.Vector;

public class SearchPresenter implements IsearchPresenter{
    private final IsearchFragment isearchFragment;
    private final MyRepository repository;
    public String f1;
    public String f2;
    public String f3;

    public String query;
    @Override
    public void search(String query,String f1,String f2,String f3,String all) {
        if (!all_meals.meals.isEmpty()) {
            repository.search(this,query, f1, f2, f3, all);
        }
    }
    public void gen(){
        repository.genrate(this);
    }

    public static final String TAG = "SearchPresenter";
    public SearchPresenter(IsearchFragment fragment,MyRepository repository)
    {
        this.repository= repository;
        this.isearchFragment=fragment;
    }

    @Override
    public void Sucess(Vector<Meal> list) {
        isearchFragment.Sucess(list);
    }
    public void onIngArraied(Vector<String> list)
    {
        isearchFragment.onSucess(list);
    }
    public void onCatArraied(Vector<String> list)
    {
        isearchFragment.onsUcess(list);
    }
    public void onAreaArraied(Vector<String> list) {
        isearchFragment.onsucess(list);
    }
    @Override
    public void Fail(String meassage) {
        isearchFragment.Fail(meassage);
    }
}
