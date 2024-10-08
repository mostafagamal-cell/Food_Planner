package com.example.foodplanner.DataSourse;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.NetWork.Iretrofit;
import com.example.foodplanner.NetWork.MyRetrofite;
import com.example.foodplanner.Repository.Irepo;
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
        iretrofit.getCategories().enqueue(categoriesCallback(Ggetcategories));
    }

    @Override
    public void filterBycategory(String category,int type) {
            iretrofit.filterByCategory(category).enqueue(mealsCallback(GfilterBycategory));
        }

    @Override
    public void filterByarea(String Area) {
        iretrofit.filterByArea(Area).enqueue(mealsCallback(GfilterByarea));
    }
    String id;
    @Override
    public synchronized void  getMealByid(String id) {
        this.id=id;
        iretrofit.getMealById(id).enqueue(mealsCallback(GgetMealByid));
    }

    @Override
    public synchronized void filterByingredient(String Ingredient) {
        iretrofit.filterByIngredient(Ingredient).enqueue(mealsCallback(GfilterByingredient));
    }

    @Override
    public void getListOfcategories(String list) {
        iretrofit.getListOfCategories("list").enqueue(categoriesCallback(GgetListOfcategories));
    }

    @Override
    public void getListOfarea() {
        iretrofit.getListOfArea("list").enqueue(countriesCallback());
    }

    @Override
    public void getListOfingredients(String list) {
        iretrofit.getListOfIngredients(list).enqueue(ingradinatsCallback(GgetListOfingredients));
    }
    public static RemoteDataSourse getInstance(Irepo.Communicator communicator) {
        if (instance==null){
            instance=new RemoteDataSourse(communicator);
        }
        instance.communicator=communicator;
        return instance ;
    }

    private static final String TAG = "RemoteDataSourse";
    public synchronized Callback<Categories> categoriesCallback(int type){
        return new Callback<Categories>(){
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                       //Log.i("216786841654684", "onResponse: "+response.body().categories.size());
                        communicator.OnListCatigoryArrived(response.body());
            }
            @Override
            public void onFailure(Call<Categories> call, Throwable throwable) {
                communicator.onError(throwable.getMessage());
            }
        } ;
    }
    public  static  int deeffe=0;
    @Override
    public synchronized Callback<Meals> mealsCallback(int type){
        return new Callback<Meals>(){
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (GfilterBycategory == type) {
                    communicator.onCatigroyMealArraiverd(response.body());
                } else if (GgetRandommeal == type) {
                    communicator.onDataRandommealArrived(response.body());
                } else if (GfilterByarea == type) {
                    communicator.onDataAreaArrived(response.body());
                } else if (GfilterByingredient == type) {
                    communicator.onDataIngradintArrived(response.body());
                } else if (GgetMealByid == type) {

                    communicator.onDataMealByIdArrived(response.body());
                }else if (GgetMealByletter == type) {
                    Log.i("ddddddddeeeeeeefgasd2145",response.code()+"");
                    communicator.onDataMealArrivedByletter(response.body());
                }
            }
            @Override
            public void onFailure(Call<Meals> call, Throwable throwable) {
                communicator.onError(throwable.getMessage());
            }
        } ;
    }

    @Override
    public synchronized Callback<Ingradiants> ingradinatsCallback(int type) {
        return new Callback<Ingradiants>(){
            @Override
            public void onResponse(Call<Ingradiants> call, Response<Ingradiants> response) {
                    communicator.onIngradintListArraived(response.body());
            }

            @Override
            public void onFailure(Call<Ingradiants> call, Throwable throwable) {
                communicator.onError(throwable.getMessage());
            }
        };
    }

    @Override
    public Callback<Countries>countriesCallback() {
        return new Callback<Countries>(){
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                int size=response.body().meals.size();
                communicator.countryListArraived(response.body());
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable throwable) {
                communicator.onError(throwable.getMessage());
            }
        };
    }
}
