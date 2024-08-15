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
import com.example.foodplanner.Util.Irepo;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.FirestoreClient;

import java.util.List;

import retrofit2.Callback;

public class MyRepository implements Irepo ,Irepo.Communicator {
  private static   MyRepository instance;
  private  Irepo.Communicator communicator;
  private LocalDataSourse localDataSourse;
  private FirebaseDatabase db;
  DatabaseReference myRef;
  private RemoteDataSourse remoteDataSourse;
  private MyRepository(Application application){


     String email= application.getSharedPreferences("user", Context.MODE_PRIVATE).getString("user",null);
      db = FirebaseDatabase.getInstance("user/"+email);
      myRef  = db.getReference();

    myRef.setValue("Hello, World!");
      remoteDataSourse = new RemoteDataSourse(this);
      localDataSourse = new LocalDataSourse(application);
  }
    public static MyRepository getInstance(Irepo.Communicator communicator,Application application) {
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
    public void getListOfingredients(String list) {
        remoteDataSourse.getListOfingredients(list);
    }

    @Override
    public void onDataArrived(Meals meals,int type) {
      communicator.onDataArrived(meals,type);
    }

    @Override
    public void onError(String message) {
  communicator.onError(message);
    }

    @Override
    public void onDataArrived(Categories categories, int type) {
      communicator.onDataArrived(categories,type);
    }

  @Override
  public Meals readFavouriteFromFireStore() {

    return Irepo.super.readFavouriteFromFireStore();
  }

  @Override
  public void writeFavouriteFromFireStore(String email, Meals JsonData) {
    myRef = db.getReference("/user");
    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot.exists()) {
          // Collection exists, delete it
          myRef.removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
              Log.d("Firebase", "Existing data deleted successfully.");

              // Create new data after deleting the old one
              myRef.setValue(JsonData).addOnCompleteListener(task1 -> {
                if (task1.isSuccessful()) {
                  Log.d("Firebase", "New data created successfully.");
                } else {
                  Log.e("Firebase", "Error creating new data", task1.getException());
                }
              });
            } else {
              Log.e("Firebase", "Error deleting existing data", task.getException());
            }
          });
        } else {
          // Collection doesn't exist, create new data
          myRef.setValue(JsonData).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
              Log.d("Firebase", "New data created successfully.");
            } else {
              Log.e("Firebase", "Error creating new data", task.getException());
            }
          });
        }
      }
      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.e("Firebase", "Error checking existence", databaseError.toException());
      }
    });


  }
}
