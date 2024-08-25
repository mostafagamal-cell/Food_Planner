package com.example.foodplanner.CatigoryItemScreen;


import android.app.Application;
import android.util.Log;

import com.example.foodplanner.App;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IcatigortItemFrag;
import com.example.foodplanner.Util.IcatigortItemPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatigoryItemPresenter implements IcatigortItemPresenter {
    private final MyRepository repo;

    private static IcatigortItemFrag icatigortItemComm;
    public CatigoryItemPresenter(IcatigortItemFrag icatigortItemFrag ,MyRepository repository) {
        repo=repository;
        icatigortItemComm=icatigortItemFrag;
    }


    @Override
    public void loadMeals(String category) {
       repo.filterBycategory(category, new Callback<Meals>() {
           @Override
           public void onResponse(Call<Meals> call, Response<Meals> response) {
               icatigortItemComm.onScuess(response.body());
           }

           @Override
           public void onFailure(Call<Meals> call, Throwable throwable) {
            icatigortItemComm.onFail(throwable.getMessage());
           }
       });
    }
}
