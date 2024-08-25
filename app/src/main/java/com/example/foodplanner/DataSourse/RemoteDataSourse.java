package com.example.foodplanner.DataSourse;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Model.PlannesMeal;
import com.example.foodplanner.NetWork.Iretrofit;
import com.example.foodplanner.NetWork.MyRetrofite;
import com.example.foodplanner.R;

import com.example.foodplanner.Util.Utilits;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.foodplanner.Util.Utilits.*;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSourse implements IremoteDataSourse {
    private static RemoteDataSourse instance;
    Iretrofit iretrofit;
    private final FirebaseFirestore db;
    private RemoteDataSourse(){
        db = FirebaseFirestore.getInstance();
        iretrofit= MyRetrofite.getInstance();
    }

    public static RemoteDataSourse getInstance() {
        if (instance == null) {
            instance = new RemoteDataSourse();
        }
        return instance;
    }

    @Override
    public void readPlanedFromFireStore(String email,OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        db.collection(email).document("P")
                .get().addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("eeeee1215411",e.getMessage());
                    }
                }).addOnCompleteListener
                        (onCompleteListener);
    }
    @Override
    public void getMealByname(String name, Callback<Meals> mealsCallback) {
        iretrofit.getMealByName(name).enqueue(mealsCallback);
    }

   
    @Override
    public void getMealByletter(String name, Callback<Meals> mealsCallback) {
        iretrofit.getMealByLetter(name).enqueue(mealsCallback);
    }

   
    @Override
    public void getRandommeal(Callback<Meals> mealsCallback) {
        iretrofit.getRandomMeal().enqueue(mealsCallback);
    }

   
    @Override
    public void getcategories(Callback<Categories> mealsCallback) {
        iretrofit.getCategories().enqueue(mealsCallback);
    }

   
    @Override
    public void filterBycategory(String category, Callback<Meals> mealsCallback) {
            iretrofit.filterByCategory(category).enqueue(mealsCallback);
        }

   
    @Override
    public void filterByarea(String Area, Callback<Meals> mealsCallback) {
        iretrofit.filterByArea(Area).enqueue(mealsCallback);
    }
    String id;
   
    @Override
    public  void  getMealByid(String id, Callback<Meals> mealsCallback) {
        iretrofit.getMealById(id).enqueue(mealsCallback);
    }

   
    @Override
    public  void filterByingredient(String Ingredient, Callback<Meals> mealsCallback) {
        iretrofit.filterByIngredient(Ingredient).enqueue(mealsCallback);
    }

   
    @Override
    public void getListOfcategories(String list, Callback<Categories> mealsCallback) {
        iretrofit.getListOfCategories("list").enqueue(mealsCallback);
    }

   
    @Override
    public void getListOfarea(Callback<Countries> mealsCallback) {
        iretrofit.getListOfArea("list").enqueue(mealsCallback);
    }

   
    @Override
    public void getListOfingredients(String list, Callback<Ingradiants> mealsCallback) {
        iretrofit.getListOfIngredients(list).enqueue(mealsCallback);
    }
    public void writePlanedFromFireStore(String email, PlannesMeal JsonData,OnCompleteListener<Void> completeListener) {
        db.collection(email).document("P").set(JsonData).addOnCompleteListener(completeListener);
    }

    public void writeFavouriteFromFireStore(String email, Meals JsonData,OnCompleteListener<Void> completeListener) {
        db.collection(email).document("f").set(JsonData).addOnCompleteListener(completeListener);
    }

    @Override
    public void readFavouriteFromFireStore(String email, OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        db.collection(email).document("f").get().addOnCompleteListener(onCompleteListener);
    }
}
