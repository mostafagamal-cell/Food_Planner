package com.example.foodplanner.IngrItem;

import android.app.Application;

import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IingPresenter;

public class IngPresenter implements IingPresenter {
    private static IingPresenter.IareaMealsPresenterComm  comm;
    private MyRepository repository;
    public static final String TAG = "IngPresenter";
    private static IngPresenter instance;
    private IngPresenter(Application application) {
        repository=MyRepository.getInstance(this,application,TAG);
    }

    public static IngPresenter getInstance(IngPresenter.IareaMealsPresenterComm comm,Application application) {
       if (instance==null)
       {
           instance=new IngPresenter(application);
       }
        IngPresenter.comm =comm;
         return instance ;
    }
    @Override
    public void loadMeals(String category) {
            repository.filterByingredient(category);
    }

    @Override
    public void onDataArrived(Meals categories) {
        comm.onDataArrived(categories);
    }
}
