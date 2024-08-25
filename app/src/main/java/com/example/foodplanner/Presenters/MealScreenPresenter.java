package com.example.foodplanner.Presenters;

import android.util.Log;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IfragmentMealComm;
import com.example.foodplanner.Util.ImealScreenPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealScreenPresenter implements ImealScreenPresenter{
   private MyRepository repository;

   public static final String name="MealScreenPresenter";
   private Categories currentCategory;
   private  static   MealScreenPresenter presenter;
   private IfragmentMealComm communicator;
   public MealScreenPresenter(IfragmentMealComm communicator,MyRepository repository){
       this.repository=repository;
       this.communicator=communicator;
   }


     @Override
    public void getRandommeal() {
            repository.getRandommeal(new Callback<Meals>() {
                @Override
                public void onResponse(Call<Meals> call, Response<Meals> response) {
                    if(response.isSuccessful()){
                        communicator.onSucess(response.body());
                    }
                }

                @Override
                public void onFailure(Call<Meals> call, Throwable throwable) {
                    communicator.onError(throwable.getMessage());
                }
            });
   }

    @Override
    public void getListOfcategories() {
    repository.getListOfcategories("list", new Callback<Categories>() {
        @Override
        public void onResponse(Call<Categories> call, Response<Categories> response) {
             if (response.isSuccessful()){
                 communicator.onSucess(response.body());
             }
        }

        @Override
        public void onFailure(Call<Categories> call, Throwable throwable) {
            communicator.onError(throwable.getMessage());
        }
    });
    }

    @Override
    public void getListOfarea() {
    repository.getListOfarea(new Callback<Countries>() {
        @Override
        public void onResponse(Call<Countries> call, Response<Countries> response) {
             if (response.isSuccessful()){
                 communicator.onSucess(response.body());
             }
        }

        @Override
        public void onFailure(Call<Countries> call, Throwable throwable) {
            communicator.onError(throwable.getMessage());
        }
    });
    }

    @Override
    public void getcatigorys() {
       repository.getcategories(new Callback<Categories>() {
           @Override
           public void onResponse(Call<Categories> call, Response<Categories> response) {
               if (response.isSuccessful()){
                   communicator.onSucess(response.body());
               }
           }

           @Override
           public void onFailure(Call<Categories> call, Throwable throwable) {
            communicator.onError(throwable.getMessage());
           }
       });
    }

    @Override
    public void getListOfingredients() {
    repository.getListOfingredients("list", new Callback<Ingradiants>() {
        @Override
        public void onResponse(Call<Ingradiants> call, Response<Ingradiants> response) {
             if (response.isSuccessful()){
                 communicator.onSucess(response.body());
             }
        }

        @Override
        public void onFailure(Call<Ingradiants> call, Throwable throwable) {
            communicator.onError(throwable.getMessage());
        }
    });
    }
}
