package com.example.foodplanner.Presenters;

import android.app.Application;
import android.util.Log;

import static com.example.foodplanner.Util.Utilits.*;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IfragmentMealComm;
import com.example.foodplanner.Util.ImealScreenPresenter;

public class MealScreenPresenter implements ImealScreenPresenter,ImealScreenPresenter.Commncator{
   private MyRepository repository;
   private  static   MealScreenPresenter presenter;
   private static Application context;
   private IfragmentMealComm communicator;
   private MealScreenPresenter(Application application){
       repository=MyRepository.getInstance(this,application);
   }
   public static MealScreenPresenter getinstance(IfragmentMealComm communicatorf, Application application){
       if (presenter==null){
           presenter=new MealScreenPresenter(application);
       }
       presenter.communicator=communicatorf;
       return presenter;
   }

    @Override
    public void onDataArrivedRandomaMeal(Meals meals) {
        communicator.onDataArrivedRandomaMeal(meals);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onDataArrivedCategories(Categories categories) {
        communicator.onDataArrivedCategories(categories);
    }

    @Override
    public void onDataArrivedIngredients(Meals meals) {
       communicator.onDataArrivedIngredients(meals);
    }

    @Override
    public void onDataArrivedCountry(Meals meals) {
        communicator.onDataArrivedCountry(meals);
    }


    public static MealScreenPresenter getInstance(IfragmentMealComm communicator,Application application) {
        if (presenter==null){
            presenter=new MealScreenPresenter(application);
        }
        presenter.communicator=communicator;
        return presenter;
    }
     @Override
    public void getRandommeal() {
    repository.getRandommeal();
    }

    @Override
    public void getListOfcategories() {
    repository.getListOfcategories("list");
    }

    @Override
    public void getListOfarea() {
    repository.getListOfarea();
    }

    @Override
    public void getListOfingredients() {
    repository.getListOfingredients("list");
    }
}
