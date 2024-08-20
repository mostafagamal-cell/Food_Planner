package com.example.foodplanner.MealPlane;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Repository.MyRepository;

import java.util.List;

public class MealPlanePresenter implements ImealPlannerPresenter{
    private static MealPlanePresenter presenter;
    private ImealPlannerPresenter.Comm comm;
    MyRepository repository;
    public static final String TAG = "MealPlanePresenter";
    private MealPlanePresenter(Application application){
        repository=MyRepository.getInstance(this,application,TAG);
    }

    @Override
    public LiveData<List<Plan>> getPlans(String email) {
       return repository.getPlanned(email);
    }

    @Override
    public void insertPlan(Plan plan) {
        repository.insertPlanned(plan);
    }
    public static MealPlanePresenter getInstance(ImealPlannerPresenter.Comm comm , Application application) {
       if (presenter==null){
           presenter=new MealPlanePresenter(application);
       }
        presenter.comm=comm;
        return presenter ;
    }
}
