package com.example.foodplanner.Util;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meals;

public interface Irepo  extends IlocalDataSource, IremoteDataSource{
interface Communicator {
    void onDataArrived(Meals meals);
    void onError(String message);
    void onDataArrived(Categories categories);
    }
}
