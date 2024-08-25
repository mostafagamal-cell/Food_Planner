package com.example.foodplanner.FavouriteScreen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IfavouriteFragment;
import com.example.foodplanner.Util.IfavouritePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class FavouritePresenter implements IfavouritePresenter {
    MyRepository repository;
    private IfavouriteFragment comm;
    public static final String TAG = "FavouritePresenter";
    public FavouritePresenter(IfavouriteFragment comm2,MyRepository m){
        repository=m;
        comm=comm2;
    }
    @Override
    public void deletFavourite(Meal meal) {
        repository.deleteFavourites(meal);
    }

    @Override
    public void saveOnClould(String email, Meals meal) {
    repository.writeFavouriteFromFireStore(email, meal, new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()){
                comm.onSucces();
            }
        }
    });
    }

    @Override
    public void readOnClould(String email) {
    repository.readFavouriteFromFireStore(email, new OnCompleteListener<DocumentSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()){
                if (task.getResult().exists()){
                    Meals meals=task.getResult().toObject(Meals.class);
                    comm.onSucces(meals);
                }
            }
        }
    });
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

        comm.onSucces(meals);
    }


}
