package com.example.foodplanner.Repository;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.DataSourse.IlocalDataSource;
import com.example.foodplanner.DataSourse.IremoteDataSource;

import java.util.List;

public interface Irepo  extends IlocalDataSource, IremoteDataSource {
      void  readFavouriteFromFireStore(String email);
      void  writeFavouriteFromFireStore(String email,Meals JsonData);
      void onError(String message);
    interface Communicator  {
        void onCatigroyMealArraiverd(Meals meals);
        void onDataMealByIdArrived(Meals meals);
        void onDataIngradintArrived(Meals meals);
    LiveData<List<Meal>>getFavourites();
    void onDataMealArrivedByname(Meals meals);
    void onDataCatigoryArrived(Meals meals,int x);
    void onDataAreaArrived(Meals meals);
    void onDataRandommealArrived(Meals meals);
    void OnListCatigoryArrived(Categories categories);
    void onIngradintListArraived(Ingradiants categories);
    void onError(String message);
    void countryListArraived(Countries countries);
    }
}
