package com.example.foodplanner.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DataSourse.LocalDataSourse;
import com.example.foodplanner.DataSourse.RemoteDataSourse;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Util.Irepo;

import java.util.List;

import retrofit2.Callback;

public class MyRepository implements Irepo ,Irepo.Communicator {
  private static   MyRepository instance;
  private LocalDataSourse localDataSourse;
  private RemoteDataSourse remoteDataSourse;
  private MyRepository(Application application){
      remoteDataSourse = new RemoteDataSourse(this);
      localDataSourse = new LocalDataSourse(application);
  }
    public static MyRepository getInstance(Application application) {
        if (instance == null){
            instance = new MyRepository(application);
        }
        return instance;
    }
    @Override
    public LiveData<List<Meal>> getFavourites() {
        return localDataSourse.getFavourites();
    }

    @Override
    public void insertFavourites(Meal meal) {
        localDataSourse.insertFavourites(meal);
    }

    @Override
    public void deleteFavourites(Meal meal) {
    localDataSourse.deleteFavourites(meal);
    }


    @Override
    public void getMealByname(String name) {
      remoteDataSourse.getMealByname(name);
    }

    @Override
    public void getMealByletter(String name) {
        remoteDataSourse.getMealByletter(name);
    }

    @Override
    public void getRandommeal() {
        remoteDataSourse.getRandommeal();
    }

    @Override
    public void getcategories() {
    remoteDataSourse.getcategories();
    }

    @Override
    public void filterBycategory(String category) {
      remoteDataSourse.filterBycategory(category);
    }

    @Override
    public void filterByarea(String Area) {
        remoteDataSourse.filterByarea(Area);
    }

    @Override
    public void getMealByid(String id) {
        remoteDataSourse.getMealByid(id);
    }

    @Override
    public void filterByingredient(String Ingredient) {
      remoteDataSourse.filterByingredient(Ingredient);
    }

    @Override
    public void getListOfcategories(String list) {
    remoteDataSourse.getListOfcategories(list);
    }

    @Override
    public void getListOfarea(String list) {
        remoteDataSourse.getListOfarea(list);
    }

    @Override
    public void getListOfingredients(String list) {
        remoteDataSourse.getListOfingredients(list);
    }

    @Override
    public void onDataArrived(Meals meals) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onDataArrived(Categories categories) {

    }
}
