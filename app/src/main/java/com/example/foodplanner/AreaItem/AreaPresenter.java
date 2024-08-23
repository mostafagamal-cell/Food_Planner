package com.example.foodplanner.AreaItem;

import android.app.Application;
import android.util.Log;

import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IareaMealsPresenter;

public class AreaPresenter  implements IareaMealsPresenter {
    private MyRepository repository;
    public static final String TAG = "AreaPresenter";
    IareaMealsPresenter.IareaMealsPresenterComm presenterComm;
    private static AreaPresenter presenter;
    private  AreaPresenter(){
        repository=MyRepository.getInstance(this,TAG);
    }

    @Override
    public void loadMeals(String area) {
        repository.filterByarea(area);
    }

    @Override
    public void onDataArrived(Meals categories) {
        Log.d("cccccccccccccccccccccccccc", "onDataArrived: "+categories.meals.size());
        presenterComm.onDataArrived(categories);
    }

    public static AreaPresenter getInstance(IareaMealsPresenter.IareaMealsPresenterComm presenterComm) {
        if (presenter==null){
            presenter=new AreaPresenter();
        }
        presenter.presenterComm=presenterComm;
        return presenter ;
    }
}
