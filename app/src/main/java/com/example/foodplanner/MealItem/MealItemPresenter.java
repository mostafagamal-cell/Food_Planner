package com.example.foodplanner.MealItem;

import android.app.Application;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Repository.MyRepository;

public class MealItemPresenter implements ImealItemPreseter,ImealItemPreseter.ImealScreenComm {
    public static final String TAG = "MealItemPresenter";

    private static MealItemPresenter instance;
    private MyRepository repository;
    private ImealItemPreseter.ImealScreenComm comm;
    private MealItemPresenter(Application application){
        repository=MyRepository.getInstance(this,application,TAG);
    }

    public static MealItemPresenter getInstance(ImealItemPreseter.ImealScreenComm comm, Application application) {
        if (instance==null){
            instance=new MealItemPresenter(application);
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
    public void dataArrived(Meal meal) {
        comm.dataArrived(meal);
    }
}
