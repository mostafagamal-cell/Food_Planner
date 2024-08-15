package com.example.foodplanner.Util;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meals;

public interface Irepo  extends IlocalDataSource, IremoteDataSource{
   default Meals  readFavouriteFromFireStore(){return  null;};
   default void  writeFavouriteFromFireStore(String email, Meals JsonData){};

    interface Communicator {
    void onDataArrived(Meals meals, int type);
    void onError(String message);
    void onDataArrived(Categories categories, int type);
    }
}
