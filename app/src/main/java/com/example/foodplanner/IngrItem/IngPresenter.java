package com.example.foodplanner.IngrItem;

import android.app.Application;

import com.example.foodplanner.MealItem.ImealItemFragment;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IingPresenter;
import com.example.foodplanner.Util.Iingfrage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngPresenter implements IingPresenter {
    private Iingfrage comm;
    private MyRepository repository;
    public static final String TAG = "IngPresenter";
    public IngPresenter(Iingfrage comm,MyRepository m) {
        this.repository=m;
        this.comm=comm;
    }

    @Override
    public void loadMeals(String category) {
            repository.filterByingredient(category, new Callback<Meals>() {
                @Override
                public void onResponse(Call<Meals> call, Response<Meals> response) {
                    if (response.isSuccessful()){
                        comm.onSuccess(response.body());
                    }
                }
                @Override
                public void onFailure(Call<Meals> call, Throwable throwable) {
                    comm.onError(throwable.getMessage());
                }
            });
    }

}
