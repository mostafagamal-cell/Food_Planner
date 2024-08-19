package com.example.foodplanner.FavouriteScreen;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IfavouritePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FavouritePresenter implements IfavouritePresenter {
    MyRepository repository;
    public static final String TAG = "FavouritePresenter";
    private static FavouritePresenter favouritePresenter;
    private FavouritePresenter(Application application){
        repository=MyRepository.getInstance(this,application,TAG);
    }
    @Override
    public void deletFavourite(Meal meal) {
        repository.deleteFavourites(meal);
    }

    @Override
    public void saveOnClould(String email, Meals meal) {
    repository.writeFavouriteFromFireStore(email,meal);
    }

    @Override
    public void readOnClould(String email) {
    repository.readFavouriteFromFireStore(email);
    }

    @Override
    public LiveData<List<Meal>> readDatafromDB() {
        return repository.getFavourites();
    }

    @Override
    public List<Meal> dataArriveFromCloud(Meals meals) {
        return Collections.emptyList();
    }

    public static FavouritePresenter getInstance(Application application) {
        if (favouritePresenter == null){
            favouritePresenter=new FavouritePresenter(application);
        }
        return  favouritePresenter;
    }

}
