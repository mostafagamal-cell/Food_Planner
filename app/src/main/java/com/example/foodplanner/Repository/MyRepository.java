package com.example.foodplanner.Repository;


import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodplanner.DataSourse.LocalDataSourse;
import com.example.foodplanner.DataSourse.RemoteDataSourse;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Model.PlannesMeal;
import com.example.foodplanner.R;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.SearchScreen.IsearchPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRepository implements ImyRepository {



  private static   MyRepository instance;
  private final LocalDataSourse localDataSourse;

  private final RemoteDataSourse remoteDataSourse;
  public static final Meals all_meals= new Meals();
  private MyRepository(LocalDataSourse localDataSourse,RemoteDataSourse remoteDataSourse){
        all_meals.meals=new ArrayList<>();
      this.remoteDataSourse = remoteDataSourse;
      this.localDataSourse=localDataSourse;
  }
    public static MyRepository getInstance(LocalDataSourse localDataSourse,RemoteDataSourse remoteDataSourse) {
        if (instance == null){
            instance = new MyRepository(localDataSourse,remoteDataSourse);
        }
        return instance;
    }

    @Override
    public LiveData<List<Meal>> getFavourites() {
        return localDataSourse.getFavourites();
    }



    @Override
    public void insertFavourites(Meal meal) {
        localDataSourse.insertFavourites(meal);
    }

    @Override
    public void deleteFavourites(Meal meal) {
    localDataSourse.deleteFavourites(meal);
    }

    @Override
    public void insertPlanned(Plan meal) {

        localDataSourse.insertPlanned(meal);
    }

    @Override
    public void deletePlanned(Plan meal) {
    localDataSourse.deletePlanned(meal);
    }

    @Override
    public LiveData<Integer> checkinDatabase(String id) {
        return localDataSourse.checkinDatabase(id);
    }




    @Override
    public void getMealByletter(String name, Callback<Meals> callback) {
      char x=name.charAt(0);
      Log.d("121131132asdasdas", "getMealByletter: "+(char)x);
        remoteDataSourse.getMealByletter(name,callback);
    }

    @Override
    public void getRandommeal(Callback<Meals> callback) {
        remoteDataSourse.getRandommeal(callback);
  }

  @Override
  public void getListOfcategories(Callback<Categories> callback) {
            remoteDataSourse.getcategories(callback);

  }

  @Override
  public void getListOfingredients(Callback<Ingradiants> callback) {
  remoteDataSourse.getListOfingredients("list",callback);
  }

  @Override
    public void getcategories(Callback<Categories> callback) {
    remoteDataSourse.getcategories(callback);
    }

    @Override
    public  void filterBycategory(String category, Callback<Meals> callback) {
      remoteDataSourse.filterBycategory(category,callback);
    }

    @Override
    public void filterByarea(String Area, Callback<Meals> callback) {
        remoteDataSourse.filterByarea(Area,callback);
    }
    @Override
    public  void getMealByid(String id, Callback<Meals> mealsCallback) {
        remoteDataSourse.getMealByid(id,mealsCallback);
    }

    @Override
    public  void  filterByingredient(String Ingredient, Callback<Meals> callback) {
      remoteDataSourse.filterByingredient(Ingredient,callback);
    }

    @Override
    public void getListOfcategories(String list, Callback<Categories> callback) {
    remoteDataSourse.getListOfcategories(list,callback);
    }

    @Override
    public void getListOfarea(Callback<Countries> callback) {

      remoteDataSourse.getListOfarea(callback);

    }

  @Override
  public void getcatigorys(Callback<Categories> callback) {
      remoteDataSourse.getcategories(callback);
  }

  @Override
    public void getListOfingredients(String list, Callback<Ingradiants> callback) {
        remoteDataSourse.getListOfingredients(list,callback);
    }

    @Override
    public void search(IsearchPresenter presenter,String query, String f1, String f2, String f3, String all) {
      Log.d("1112223458adasdas", "search: "+query+" "+f1+" "+f2+" "+f3+" "+all);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Vector<Meal> meals = new Vector<>();
                for (int i = 0; i < all_meals.meals.size(); i++) {
                    Meal meal=all_meals.meals.get(i);

                    boolean containsQuery = meal.strMeal.toLowerCase().contains(query.toLowerCase());
                    // Check if the meal matches the category, area, and ingredient
                    boolean matchesCategory = f1.equalsIgnoreCase(all) || meal.strArea.equalsIgnoreCase(f1);
                    boolean matchesArea = f2.equalsIgnoreCase(all) || meal.strCategory.equalsIgnoreCase(f2);
                    boolean matchesIngredient = f3.equalsIgnoreCase(all);

                    for (int j = 1; j <= 20; j++) {
                        try {
                            Field ingredientField = Meal.class.getDeclaredField("strIngredient" + j);
                            ingredientField.setAccessible(true);
                            String ingredient = (String) ingredientField.get(meal);

                            if (ingredient != null && ingredient.equalsIgnoreCase(f3)) {
                                matchesIngredient = true;
                                break;
                            }
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            Log.e("SearchError", e.getMessage());
                        }
                    }

                    // If the meal matches the query, category, area, and ingredient, add it to the list
                    if (containsQuery && matchesCategory && matchesArea && matchesIngredient) {
                        meals.add(meal);
                    }
                }
                presenter.Sucess(meals);
            }
        }).start();
    }


    @Override
    public LiveData<List<Plan>> getPlanned(String email) {
        return localDataSourse.getPlanned(email);
    }

    @Override
  public void readFavouriteFromFireStore(String email, OnCompleteListener<DocumentSnapshot> completeListener) {
    remoteDataSourse.readFavouriteFromFireStore(email,completeListener);
  }

    @Override
    public void writePlanedFromFireStore(String email, PlannesMeal JsonData, OnCompleteListener<Void> completeListener) {
      remoteDataSourse.writePlanedFromFireStore(email,JsonData,completeListener);
    }

    @Override
    public void readPlanedFromFireStore(String email, OnCompleteListener<DocumentSnapshot> completeListener) {
        remoteDataSourse.readPlanedFromFireStore(email,completeListener);
    }


    @Override
    public void writeFavouriteFromFireStore(String email, Meals JsonData, OnCompleteListener<Void> completeListener) {
        remoteDataSourse.writeFavouriteFromFireStore(email,JsonData,completeListener);
  }

    public static Vector<String>all_cat=new Vector<>();
    public static Vector<String>areas =new Vector<>();
    public static Vector<String>all_ing=new Vector<>();
    public  void genrate(IsearchPresenter presenter){
        for (int i = 0; i < 26; i++) {
            final int dd=i;
            String xsd=((char)('a'+i))+"";
            Log.d("121131132asdasdas", "genrate: "+xsd);
            getMealByletter((char)('a'+i)+"",new Callback<Meals>(){
                @Override
                public void onResponse(Call<Meals> call, Response<Meals> response) {
                    if (response.isSuccessful())
                        if (response.body().meals!=null) all_meals.meals.addAll(response.body().meals);
                        if (((char)(dd+'a'))=='z'){
                            Vector<Meal> m=new Vector<>();
                            for (int j = 0; j < all_meals.meals.size(); j++) {
                                m.addAll(all_meals.meals);
                            }
                            presenter.Sucess(m);
                        }
                    Log.d("121131132asdasdas", ((char)'a'+dd)+" "+all_meals.meals.size());
                }

                @Override
                public void onFailure(Call<Meals> call, Throwable throwable) {

                }
            });
        }
       getListOfcategories(new Callback<Categories>() {
           @Override
           public void onResponse(Call<Categories> call, Response<Categories> response) {
               if (response.isSuccessful()){
                   for (int i = 0; i < response.body().categories.size(); i++) {
                       if (!all_cat.contains(response.body().categories.get(i).strCategory))
                           all_cat.add(response.body().categories.get(i).strCategory);
                   }
                   presenter.onCatArraied(all_cat);
               }
           }

           @Override
           public void onFailure(Call<Categories> call, Throwable throwable) {

           }
       });
        if (areas.isEmpty()) getListOfarea(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                        if (response.isSuccessful()){
                            for (int i = 0; i < response.body().meals.size(); i++) {
                                areas.add(response.body().meals.get(i).strArea);
                            }
                            presenter.onAreaArraied(areas);
                        }
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable throwable) {

            }
        });
        if (all_ing.isEmpty()) getListOfingredients(new Callback<Ingradiants>() {
            @Override
            public void onResponse(Call<Ingradiants> call, Response<Ingradiants> response) {
                if (response.isSuccessful()){
                    for (int i = 0; i < response.body().meals.size(); i++) {
                        all_ing.add(response.body().meals.get(i).strIngredient);
                    }
                    presenter.onIngArraied(all_ing);
                }
            }
            @Override
            public void onFailure(Call<Ingradiants> call, Throwable throwable) {

            }
        });
        Log.d("eeeeeeeeeeeeeeeeeeeeeeeeeeeeee", ""+all_meals.meals.size());

    }

}
