package com.example.foodplanner.Repository;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.DataSourse.IlocalDataSource;
import com.example.foodplanner.DataSourse.IremoteDataSource;
import com.example.foodplanner.Util.ImealScreenPresenter;

import java.util.List;

public interface Irepo  extends IlocalDataSource, IremoteDataSource {
      void  readFavouriteFromFireStore();
      void  writeFavouriteFromFireStore(String email,Meals JsonData);
      void onError(String message);
    interface Communicator  {
    LiveData<List<Meal>>getFavourites();
    void onDataCatigoryArrived(Meals meals);
    void onDataRandommealArrived(Meals meals);
    void onDataArrivedFavourite(Meals meals);
    void OnListCatigoryArrived(Categories categories);
    void onError(String message);
    }
}
