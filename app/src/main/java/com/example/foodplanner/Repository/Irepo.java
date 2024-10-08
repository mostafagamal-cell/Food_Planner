package com.example.foodplanner.Repository;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.DataSourse.IlocalDataSource;
import com.example.foodplanner.DataSourse.IremoteDataSource;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Model.PlannesMeal;

import java.util.ArrayList;
import java.util.List;

public interface Irepo  extends IlocalDataSource, IremoteDataSource {
     void search(String query,String f1,String f2,String f3);
      LiveData<List<Plan>> getPlanned(String email);
      void  readFavouriteFromFireStore(String email);
      void  writePlanedFromFireStore(String email, PlannesMeal JsonData);
      void  readPlanedFromFireStore(String email);
      void  writeFavouriteFromFireStore(String email,Meals JsonData);
      void onError(String message);
    interface Communicator  {
        void onCatigroyMealArraiverd(Meals meals);
        void onDataMealByIdArrived(Meals meals);
        void onDataIngradintArrived(Meals meals);
    LiveData<List<Meal>>getFavourites();
        void onDataMealArrivedByletter(Meals meals);

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
