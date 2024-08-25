package com.example.foodplanner.AreaItem;

import android.app.Application;
import android.util.Log;

import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IareaFrag;
import com.example.foodplanner.Util.IareaMealsPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AreaPresenter  implements IareaMealsPresenter {
    private MyRepository repository;
    public static final String TAG = "AreaPresenter";
    IareaFrag presenterComm;
    private static AreaPresenter presenter;
    public   AreaPresenter(IareaFrag iareaFrag, MyRepository myRepository){
        repository=myRepository;
        presenterComm=iareaFrag;
    }

    @Override
    public void loadMeals(String area) {
        repository.filterByarea(area, new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful())presenterComm.onSucess(response.body());
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable throwable) {
                presenterComm.fail(throwable.getMessage());
            }
        } );
    }
}
