package com.example.foodplanner.Presenters;

import android.app.Application;
import android.util.Log;

import static com.example.foodplanner.Util.Utilits.*;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IfragmentMealComm;
import com.example.foodplanner.Util.ImealScreenPresenter;

import java.util.ArrayList;

public class MealScreenPresenter implements ImealScreenPresenter,ImealScreenPresenter.Commncator{
   private MyRepository repository;
   private Meals currentMeal;
   public static final String name="MealScreenPresenter";
   private Categories currentCategory;
   private  static   MealScreenPresenter presenter;
   private IfragmentMealComm communicator;
   private MealScreenPresenter(){
       repository=MyRepository.getInstance(this,name);
   }
   public static MealScreenPresenter getinstance(IfragmentMealComm communicatorf){
       if (presenter==null){
           presenter=new MealScreenPresenter();
       }
       presenter.communicator=communicatorf;
       return presenter;
   }

    @Override
    public void onDataArrivedRandomaMeal(Meals meals) {
        this.currentMeal=meals;
        communicator.onDataArrivedRandomaMeal(currentMeal);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onDataArrivedCategories(Categories categories) {
        Log.d("xxxxxxxeeeeeeeeeeeeeeeeeeeeeee", "onDataArrivedCategories() called with: categories = [" + categories.categories.size() + "]");
        if (communicator!=null)
            communicator.onDataArrivedCategories(categories);
        this.currentCategory=categories;
    }

    @Override
    public void onDataArrivedIngredients(Ingradiants meals) {
       communicator.onDataArrivedIngredients(meals);
    }

    @Override
    public void onDataArrivedCountry(Countries meals) {
        communicator.onDataArrivedCountry(meals);
    }


    public static MealScreenPresenter getInstance(IfragmentMealComm communicator) {
        if (presenter==null){
            presenter=new MealScreenPresenter();
        }
        presenter.communicator=communicator;
        return presenter;
    }
     @Override
    public void getRandommeal() {
       if (currentMeal==null)
            repository.getRandommeal();
       else{

           onDataArrivedRandomaMeal(currentMeal);
    }
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
    public void getcatigorys() {
       if (currentCategory==null)
            repository.getcategories();
       else
           onDataArrivedCategories(currentCategory);
    }

    @Override
    public void getListOfingredients() {
    repository.getListOfingredients("list");
    }
}
