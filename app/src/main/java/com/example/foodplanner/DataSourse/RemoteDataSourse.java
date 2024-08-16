package com.example.foodplanner.DataSourse;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.NetWork.Iretrofit;
import com.example.foodplanner.NetWork.MyRetrofite;
import com.example.foodplanner.Repository.Irepo;
import com.example.foodplanner.Util.ImealScreenPresenter;
import com.example.foodplanner.Util.Utilits;
import static com.example.foodplanner.Util.Utilits.*;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSourse implements IremoteDataSource {
    private static RemoteDataSourse instance;
    Iretrofit iretrofit;
    Irepo.Communicator communicator;
    public RemoteDataSourse(Irepo.Communicator communicator){
        iretrofit= MyRetrofite.getInstance();
        this.communicator=communicator;
    }
    @Override
    public void getMealByname(String name) {
        iretrofit.getMealByName(name).enqueue(mealsCallback(Gbyname));
    }

    @Override
    public void getMealByletter(String name) {
        iretrofit.getMealByLetter(name).enqueue(mealsCallback(Utilits.GgetMealByletter));
    }

    @Override
    public void getRandommeal() {
        iretrofit.getRandomMeal().enqueue(mealsCallback(GgetRandommeal));
    }

    @Override
    public void getcategories() {
        Log.i(TAG, "getcategories: ");
        iretrofit.getCategories().enqueue(categoriesCallback(Ggetcategories));
    }

    @Override
    public void filterBycategory(String category) {
        iretrofit.filterByCategory(category).enqueue(mealsCallback(GfilterBycategory));
    }

    @Override
    public void filterByarea(String Area) {
        iretrofit.filterByArea(Area).enqueue(mealsCallback(GfilterByarea));
    }

    @Override
    public void getMealByid(String id) {
        iretrofit.getMealById(id).enqueue(mealsCallback(GgetMealByid));
    }

    @Override
    public void filterByingredient(String Ingredient) {
        iretrofit.filterByIngredient(Ingredient).enqueue(mealsCallback(GfilterByingredient));
    }

    @Override
    public void getListOfcategories(String list) {
        iretrofit.getListOfCategories(list).enqueue(categoriesCallback(GgetListOfcategories));
    }

    @Override
    public void getListOfarea() {
        iretrofit.getListOfArea("list").enqueue(mealsCallback(GgetListOfarea));
    }

    @Override
    public void getListOfingredients(String list) {
        iretrofit.getListOfIngredients(list).enqueue(mealsCallback(GgetListOfingredients));
    }

    public static RemoteDataSourse getInstance(Irepo.Communicator communicator) {
        if (instance==null){
            instance=new RemoteDataSourse(communicator);
        }
        instance.communicator=communicator;
        return instance ;
    }

    private static final String TAG = "RemoteDataSourse";
    public Callback<Categories> categoriesCallback(int type){
        return new Callback<Categories>(){

            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                Log.i(TAG, "onResponse: "+response.body().categories.size()+"");
                communicator.OnListCatigoryArrived(response.body());
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable throwable) {
                communicator.onError(throwable.getMessage());
            }
        } ;
    }
    @Override
    public Callback<Meals> mealsCallback(int type){
        return new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
              communicator.onDataRandommealArrived(response.body());
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable throwable) {
                communicator.onError(throwable.getMessage());
            }
        } ;
    }

}
