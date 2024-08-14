package com.example.foodplanner.Presenters;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import static com.example.foodplanner.Util.Utilits.*;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.ImealScreenPresenter;
import com.example.foodplanner.Util.Irepo;
import com.example.foodplanner.Util.Utilits;

import java.util.List;

public class MealScreenPresenter implements Irepo.Communicator,Irepo {
   private MyRepository repository;
   private  static   MealScreenPresenter presenter;
   private static Application context;
   private ImealScreenPresenter communicator;
   private MealScreenPresenter(Application application){
       repository=MyRepository.getInstance(this,application);
   }
   public static MealScreenPresenter getinstance(ImealScreenPresenter communicatorf,Application application){
       if (presenter==null){
           presenter=new MealScreenPresenter(application);
       }
       presenter.communicator=communicatorf;
       return presenter;
   }
    @Override
    public void onDataArrived(Meals meals, int type) {
        Log.i("xxxxxxxxxxxxxxxxxxxx",meals.meals.size()+"");
        switch (type){
            case GgetRandommeal :communicator.onDataArrivedRandomaMeal(meals);

        }
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onDataArrived(Categories categories, int type) {

    }

    public static MealScreenPresenter getInstance(ImealScreenPresenter communicator,Application application) {
        if (presenter==null){
            presenter=new MealScreenPresenter(application);
        }
        presenter.communicator=communicator;
        return presenter;
    }

    @Override
    public LiveData<List<Meal>> getFavourites() {
        return repository.getFavourites();
    }

    @Override
    public void insertFavourites(Meal meal) {
        repository.insertFavourites(meal);
    }

    @Override
    public void deleteFavourites(Meal meal) {
        repository.deleteFavourites(meal);
    }

    @Override
    public void getMealByname(String name) {
        repository.getMealByname(name);
    }

    @Override
    public void getMealByletter(String name) {
        repository.getMealByletter(name);
    }

    @Override
    public void getRandommeal() {
    repository.getRandommeal();
    }

    @Override
    public void getcategories() {
    repository.getcategories();
    }

    @Override
    public void filterBycategory(String category) {
    repository.filterBycategory(category);

    }

    @Override
    public void filterByarea(String Area) {
    repository.filterByarea(Area);
    }

    @Override
    public void getMealByid(String id) {
        repository.getMealByid(id);
    }

    @Override
    public void filterByingredient(String Ingredient) {
    repository.filterByingredient(Ingredient);
    }

    @Override
    public void getListOfcategories(String list) {
    repository.getListOfcategories(list);
    }

    @Override
    public void getListOfarea() {
    repository.getListOfarea();
    }

    @Override
    public void getListOfingredients(String list) {
    repository.getListOfingredients(list);
    }
}
