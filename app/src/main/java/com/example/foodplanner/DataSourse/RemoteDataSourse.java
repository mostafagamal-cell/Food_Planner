package com.example.foodplanner.DataSourse;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.NetWork.Iretrofit;
import com.example.foodplanner.NetWork.MyRetrofite;
import com.example.foodplanner.Util.IremoteDataSource;
import com.example.foodplanner.Util.Irepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSourse implements IremoteDataSource {
    private static RemoteDataSourse instance;
    Iretrofit iretrofit;
    Irepo.Communicator communicator;
    private RemoteDataSourse( Irepo.Communicator communicator){
        iretrofit= MyRetrofite.getInstance();
        this.communicator=communicator;
    }
    @Override
    public void getMealByname(String name) {
        iretrofit.getMealByName(name).enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
              communicator.onDataArrived(response.body());
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable throwable) {
                communicator.onError(throwable.getMessage());

            }
        });

    }

    @Override
    public void getMealByletter(String name) {
        iretrofit.getMealByLetter(name).enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                communicator.onDataArrived(response.body());
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable throwable) {
                communicator.onError(throwable.getMessage());

            }
        });
    }

    @Override
    public void getRandommeal() {
        iretrofit.getRandomMeal().enqueue(mealsCallback());
    }

    @Override
    public void getcategories() {
        iretrofit.getCategories().enqueue(categoriesCallback());
    }

    @Override
    public void filterBycategory(String category) {
        iretrofit.filterByCategory(category).enqueue(mealsCallback());
    }

    @Override
    public void filterByarea(String Area) {
        iretrofit.filterByArea(Area).enqueue(mealsCallback());
    }

    @Override
    public void getMealByid(String id) {
        iretrofit.getMealById(id).enqueue(mealsCallback());
    }

    @Override
    public void filterByingredient(String Ingredient) {
        iretrofit.filterByIngredient(Ingredient).enqueue(mealsCallback());
    }

    @Override
    public void getListOfcategories(String list) {
        iretrofit.getListOfCategories(list).enqueue(mealsCallback());
    }

    @Override
    public void getListOfarea(String list) {
        iretrofit.getListOfArea(list).enqueue(mealsCallback());
    }

    @Override
    public void getListOfingredients(String list) {
        iretrofit.getListOfIngredients(list).enqueue(mealsCallback());
    }

    public static RemoteDataSourse getInstance(Irepo.Communicator communicator) {
        if (instance==null){
            instance=new RemoteDataSourse(communicator);
        }
        instance.communicator=communicator;
        return instance ;
    }
    public Callback<Categories> categoriesCallback(){
        return new Callback<Categories>(){

            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                communicator.onDataArrived(response.body());
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable throwable) {
                communicator.onError(throwable.getMessage());
            }
        } ;
    }
    @Override
    public Callback<Meals> mealsCallback(){
        return new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                communicator.onDataArrived(response.body());
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable throwable) {
                communicator.onError(throwable.getMessage());
            }
        } ;
    }

}
