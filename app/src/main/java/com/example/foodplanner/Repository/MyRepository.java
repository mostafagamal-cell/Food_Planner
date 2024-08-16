package com.example.foodplanner.Repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodplanner.DataSourse.LocalDataSourse;
import com.example.foodplanner.DataSourse.RemoteDataSourse;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Util.IfragmentMealComm;
import com.example.foodplanner.Util.ImealScreenPresenter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyRepository implements Irepo,Irepo.Communicator,ImealScreenPresenter {
  private static   MyRepository instance;
  private  ImealScreenPresenter.Commncator communicator;
    private static final String TAG = "MyRepository";
  private LocalDataSourse localDataSourse;
  private FirebaseFirestore db;
  CollectionReference myRef;
  private RemoteDataSourse remoteDataSourse;
  private MyRepository(Application application){
     String email= application.getSharedPreferences("user", Context.MODE_PRIVATE).getString("user",null);
      db = FirebaseFirestore.getInstance();
      myRef  = db.collection("users");

      remoteDataSourse = new RemoteDataSourse(this);
      localDataSourse = new LocalDataSourse(application);
  }
    public static MyRepository getInstance(ImealScreenPresenter.Commncator communicator, Application application) {
        if (instance == null){
            instance = new MyRepository(application);
        }
        instance.communicator=communicator;
        return instance;
    }
    @Override
    public LiveData<List<Meal>> getFavourites() {
        return localDataSourse.getFavourites();
    }

  @Override
  public void onDataRandommealArrived(Meals meals) {
  communicator.onDataArrivedRandomaMeal(meals);
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
    public void getMealByname(String name) {
      remoteDataSourse.getMealByname(name);
    }

    @Override
    public void getMealByletter(String name) {
        remoteDataSourse.getMealByletter(name);
    }

    @Override
    public void getRandommeal() {
        remoteDataSourse.getRandommeal();
    }

  @Override
  public void getListOfcategories() {
    remoteDataSourse.getListOfcategories("list");
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
    public void filterBycategory(String category) {
      remoteDataSourse.filterBycategory(category);
    }

    @Override
    public void filterByarea(String Area) {
        remoteDataSourse.filterByarea(Area);
    }

    @Override
    public void getMealByid(String id) {
        remoteDataSourse.getMealByid(id);
    }

    @Override
    public void filterByingredient(String Ingredient) {
      remoteDataSourse.filterByingredient(Ingredient);
    }

    @Override
    public void getListOfcategories(String list) {
    remoteDataSourse.getListOfcategories(list);
    }

    @Override
    public void getListOfarea() {
        remoteDataSourse.getListOfarea();
    }

  @Override
  public void getcatigorys() {

  }

  @Override
    public void getListOfingredients(String list) {
        remoteDataSourse.getListOfingredients(list);
    }

    @Override
    public void onError(String message) {

    }


  @Override
  public void readFavouriteFromFireStore() {

  }

  @Override
  public void writeFavouriteFromFireStore(String email, Meals JsonData) {
    db.collection("users")
            .add(email)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
              @Override
              public void onSuccess(DocumentReference documentReference) {
                Log.d("xxxxxxxxxxxxxxxxxxxxxxxxx", "DocumentSnapshot added with ID: " + documentReference.getId());
              }
            })
            .addOnFailureListener(new OnFailureListener() {
              @Override
              public void onFailure(@NonNull Exception e) {
                Log.w("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", "Error adding document", e);
              }
            });

  }

  @Override
  public void onDataArrivedFavourite(Meals meals) {
  }

  @Override
  public void OnListCatigoryArrived(Categories categories) {
    communicator.onDataArrivedCategories(categories);
  }
}
