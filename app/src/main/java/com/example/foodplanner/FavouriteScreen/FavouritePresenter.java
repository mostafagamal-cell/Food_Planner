package com.example.foodplanner.FavouriteScreen;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IfavouritePresenter;

import java.util.List;

public class FavouritePresenter implements IfavouritePresenter {
    MyRepository repository;
    private IfavouritePresenter.COMM comm;
    public static final String TAG = "FavouritePresenter";
    private static FavouritePresenter favouritePresenter;
    private FavouritePresenter(){
        repository=MyRepository.getInstance(this,TAG);
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
    public void saveOnDB(Meal meal) {
        repository.insertFavourites(meal);
    }

    @Override
    public LiveData<List<Meal>> readDatafromDB() {
        return repository.getFavourites();
    }

    @Override
    public void dataArriveFromCloud(Meals meals) {
        if (comm!=null) comm.onDataArrive(meals);
    }

    public static FavouritePresenter getInstance(IfavouritePresenter.COMM comm) {
        if (favouritePresenter == null){
            favouritePresenter=new FavouritePresenter();
        }
        favouritePresenter.comm=comm;
        return  favouritePresenter;
    }

}
