package com.example.foodplanner.Repository;


import android.app.Application;
import android.hardware.Camera;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodplanner.AreaItem.AreaPresenter;
import com.example.foodplanner.DataSourse.LocalDataSourse;
import com.example.foodplanner.DataSourse.RemoteDataSourse;
import com.example.foodplanner.CatigoryItemScreen.CatigoryItemPresenter;
import com.example.foodplanner.FavouriteScreen.FavouritePresenter;
import com.example.foodplanner.IngrItem.IngPresenter;
import com.example.foodplanner.MealItem.ImealItemPreseter;
import com.example.foodplanner.MealItem.MealItemPresenter;
import com.example.foodplanner.MealPlane.MealPlanePresenter;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Model.PlannesMeal;
import com.example.foodplanner.Util.IareaMealsPresenter;
import com.example.foodplanner.Util.IcatigortItemPresenter;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Presenters.MealScreenPresenter;
import com.example.foodplanner.Util.IfavouritePresenter;
import com.example.foodplanner.Util.IingPresenter;
import com.example.foodplanner.Util.ImainPresenter;
import com.example.foodplanner.Util.ImealScreenPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MyRepository implements Irepo,Irepo.Communicator,ImealScreenPresenter {
  public static Countries countries;
    public static Categories categories;
    public static Meals currentMeal;
    private  static MealPlanePresenter mealPlanePresenter;
  private static   MyRepository instance;
  private  ImealItemPreseter.ImealScreenComm imealItemPreseter;
  private IareaMealsPresenter iareaMealsPresenter;
  private IingPresenter iingPresenter;
    private  ImealScreenPresenter.Commncator Imealscreenpresenter;
  private  IcatigortItemPresenter icatigortItemComm;
  private static final String TAG = "MyRepository";
  private final LocalDataSourse localDataSourse;
  private final FirebaseFirestore db;
  private  final Application application;
  private static IfavouritePresenter ifavouritePresenter;
  private final RemoteDataSourse remoteDataSourse;
  private  Meals all_meals= new Meals();
  private MyRepository(Application application){
      db = FirebaseFirestore.getInstance();
        this.application=application;
      remoteDataSourse = new RemoteDataSourse(this);
      localDataSourse = new LocalDataSourse(application);
  }
    public static MyRepository getInstance(ImainPresenter presenter, Application application,String type) {
        if (instance == null){
            instance = new MyRepository(application);
        }
        switch (type) {
            case MealScreenPresenter.name:
                instance.Imealscreenpresenter = (ImealScreenPresenter.Commncator) presenter;
                break;
            case CatigoryItemPresenter.name:
                instance.icatigortItemComm = (IcatigortItemPresenter) presenter;
                break;
            case AreaPresenter.TAG:
                instance.iareaMealsPresenter = (IareaMealsPresenter) presenter;
                break;
            case IngPresenter.TAG:
                instance.iingPresenter=(IingPresenter) presenter;
                break;
            case MealItemPresenter.TAG:
                instance.imealItemPreseter= (ImealItemPreseter.ImealScreenComm) presenter;
                break;
            case FavouritePresenter.TAG:
                ifavouritePresenter = (IfavouritePresenter) presenter;break;
            case MealPlanePresenter.TAG: mealPlanePresenter = (MealPlanePresenter) presenter;break;
        }
        return instance;
    }

    @Override
    public void onDataIngradintArrived(Meals meals) {
      if (iingPresenter!=null)
        iingPresenter.onDataArrived(meals);
    }

    @Override
    public LiveData<List<Meal>> getFavourites() {
        return localDataSourse.getFavourites();
    }
    int x=0;
    @Override
    public void onDataMealArrivedByletter(Meals meals) {

      if (all_meals.meals==null){
          all_meals.meals=new ArrayList<>();
      }
      if (meals.meals!=null) {
          all_meals.meals.addAll(meals.meals);
          Log.i("ea1233321dsdadadas  "+((char)('a'+x)), all_meals.meals.size() + "");
      }else{
          Log.i("ea1233321dsdadadas  "+((char)('a'+x)), "null");
      }
      x++;
    }

    @Override
    public void onDataMealArrivedByname(Meals meals) {

    }

    @Override
    public void onDataCatigoryArrived(Meals meals,int a) {
      if (icatigortItemComm!=null)
            icatigortItemComm.onDataArrived(meals);

  }

    @Override
    public void onDataAreaArrived(Meals meals) {
        if (icatigortItemComm!=null) icatigortItemComm.onDataArrived(meals);
        if (iareaMealsPresenter!=null)iareaMealsPresenter.onDataArrived(meals);
        if (iingPresenter!=null)iingPresenter.onDataArrived(meals);
    }

    @Override
  public void onDataRandommealArrived(Meals meals) {
           currentMeal=meals;
            if (Imealscreenpresenter!=null) Imealscreenpresenter.onDataArrivedRandomaMeal(currentMeal);
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
    public void getMealByname(String name) {
      remoteDataSourse.getMealByname(name);
    }

    @Override
    public void getMealByletter(String name) {
        remoteDataSourse.getMealByletter(name);
    }

    @Override
    public void getRandommeal() {
      if (currentMeal==null)
        remoteDataSourse.getRandommeal();
      else
          onDataRandommealArrived(currentMeal);
  }

  @Override
  public void getListOfcategories() {
      if (categories==null)
            remoteDataSourse.getcategories();
      else
          OnListCatigoryArrived(categories);
  }

  @Override
  public void getListOfingredients() {
  remoteDataSourse.getListOfingredients("list");
  }

  @Override
    public void getcategories() {
    remoteDataSourse.getcategories();
    }

    @Override
    public synchronized void filterBycategory(String category,int type) {
      remoteDataSourse.filterBycategory(category,type);
    }

    @Override
    public void filterByarea(String Area) {
        remoteDataSourse.filterByarea(Area);
    }
    @Override
    public synchronized void getMealByid(String id) {
        remoteDataSourse.getMealByid(id);
    }

    @Override
    public synchronized void  filterByingredient(String Ingredient) {
      remoteDataSourse.filterByingredient(Ingredient);
    }

    @Override
    public void getListOfcategories(String list) {
    remoteDataSourse.getListOfcategories(list);
    }

    @Override
    public void getListOfarea() {
        if (countries==null)
            remoteDataSourse.getListOfarea();
        else
            countryListArraived(countries);
    }

  @Override
  public void getcatigorys() {
      remoteDataSourse.getcategories();
  }

  @Override
    public void getListOfingredients(String list) {
        remoteDataSourse.getListOfingredients(list);
    }

    @Override
    public void onError(String message) {

    }



    @Override
    public synchronized void  onCatigroyMealArraiverd(Meals meals) {
        if (icatigortItemComm!=null){
            icatigortItemComm.onDataArrived(meals);
        }

    }
    public  static  int xxx;
    @Override
    public synchronized void  onDataMealByIdArrived(Meals meals) {
        if (imealItemPreseter!=null)
            imealItemPreseter.dataArrived(meals.meals.get(0));

    }

    @Override
    public LiveData<List<Plan>> getPlanned(String email) {
        return localDataSourse.getPlanned(email);
    }

    @Override
  public void readFavouriteFromFireStore(String email) {
        db.collection(email).document("f").get().addOnCompleteListener
                (new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()){
                            Meals meals = document.toObject(Meals.class);
                            if (ifavouritePresenter!=null)
                                ifavouritePresenter.dataArriveFromCloud(meals);
                        }
                    }
                    }
                });

  }

    @Override
    public void writePlanedFromFireStore(String email, PlannesMeal JsonData) {
        db.collection(email).document("P").set(JsonData).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(application, "Success", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(application, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    @Override
    public void readPlanedFromFireStore(String email) {
        db.collection(email).document("P").get().addOnCompleteListener
                (new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                PlannesMeal meals = document.toObject(PlannesMeal.class);
                                if (mealPlanePresenter!=null)
                                    mealPlanePresenter.dataArrived(meals);
                            }
                        }
                    }
                });
    }

    @Override
  public void writeFavouriteFromFireStore(String email, Meals JsonData) {
    db.collection(email).document("f").set(JsonData).addOnCompleteListener(
        new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(application, "Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(application, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    );

  }


  @Override
  public void OnListCatigoryArrived(Categories categories) {
        MyRepository.categories =categories;
      if (Imealscreenpresenter != null) {
          Imealscreenpresenter.onDataArrivedCategories(categories);
      }
  }

    @Override
    public void onIngradintListArraived(Ingradiants categories) {
      if (Imealscreenpresenter!=null)
        Imealscreenpresenter.onDataArrivedIngredients(categories);
    }

    @Override
    public void countryListArraived(Countries countries) {
             MyRepository.countries =countries;
             if (Imealscreenpresenter!=null)
                 Imealscreenpresenter.onDataArrivedCountry(countries);
    }

}
