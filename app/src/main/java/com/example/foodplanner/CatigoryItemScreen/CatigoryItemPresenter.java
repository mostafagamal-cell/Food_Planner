package com.example.foodplanner.CatigoryItemScreen;

import android.app.Application;
import android.util.Log;

import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IcatigortItemPresenter;

public class CatigoryItemPresenter implements IcatigortItemPresenter {
    public final static  String name="CatigoryItemPresenter";
    private final MyRepository repo;
    private Application application;
    private static IcatigortItemPresenter.IcatigortItemComm icatigortItemComm;
    private static CatigoryItemPresenter catigoryItemPresenter;
    private CatigoryItemPresenter() {
        repo=MyRepository.getInstance(this,application,name);
    }

    public static CatigoryItemPresenter getInstance(IcatigortItemPresenter.IcatigortItemComm icatigortItemComm,Application application) {
        if (catigoryItemPresenter == null){
            catigoryItemPresenter= new CatigoryItemPresenter();
        }
        CatigoryItemPresenter.icatigortItemComm =icatigortItemComm;
        return catigoryItemPresenter;
    }

    @Override
    public void loadMeals(String category) {
       repo.filterBycategory(category);
    }

    @Override
    public void onDataArrived(Meals categories) {
        icatigortItemComm.onDataArrived(categories);
    }
}
