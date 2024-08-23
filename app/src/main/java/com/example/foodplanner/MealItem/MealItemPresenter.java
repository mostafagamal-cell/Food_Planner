package com.example.foodplanner.MealItem;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Repository.MyRepository;

public class MealItemPresenter implements ImealItemPreseter,ImealItemPreseter.ImealScreenComm {
    public static final String TAG = "MealItemPresenter";

    private static MealItemPresenter instance;
    private MyRepository repository;
    private ImealItemPreseter.ImealScreenComm comm;
    private MealItemPresenter(){
        repository=MyRepository.getInstance(this,TAG);
    }

    public static MealItemPresenter getInstance(ImealItemPreseter.ImealScreenComm comm) {
        if (instance==null){
            instance=new MealItemPresenter();
        }
        instance.comm=comm;
        return instance ;
    }

    @Override
    public void instertMeal(Meal meal) {
        repository.insertFavourites(meal);
    }


    @Override
    public void loadMealById(Meal meal) {
     repository.getMealByid(meal.idMeal);
    }

    @Override
    public void saveMeal(Meal meal) {
        repository.insertFavourites(meal);
    }

    @Override
    public void savePlan(Plan plan) {
        repository.insertPlanned(plan);

    }

    @Override
    public void dataArrived(Meal meal) {
        comm.dataArrived(meal);
    }

    @Override
    public LiveData<Integer> checkinDatabase(String id) {
        return repository.checkinDatabase(id);
    }
}
