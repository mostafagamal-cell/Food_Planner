package com.example.foodplanner.MealPlane;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Model.PlannesMeal;
import com.example.foodplanner.Repository.MyRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.type.DateTime;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MealPlanePresenter implements ImealPlannerPresenter{
    private static MealPlanePresenter presenter;
    public String f1;
    public String f2;
    private ImealPlannerFrag comm;
    MyRepository repository;
    public static final String TAG = "MealPlanePresenter";
    public MealPlanePresenter(ImealPlannerFrag frag,MyRepository myRepository){
        repository=myRepository;
        this.comm=frag;
    }

    @Override
    public LiveData<List<Plan>> getPlans(String email) {
       return repository.getPlanned(email);
    }

    @Override
    public void insertPlan(Plan plan) {
        repository.insertPlanned(plan);
    }


    @Override
    public void loadplane(String email) {
        repository.readPlanedFromFireStore(email,new OnCompleteListener<DocumentSnapshot>(){

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                       PlannesMeal meals = document.toObject(PlannesMeal.class);
                       comm.onSucces(meals.meals);
                   }
               }
            }
        });
    }

    @Override
    public void saveplans(PlannesMeal plan, String email) {
    repository.writePlanedFromFireStore(email, plan, new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()){
                    comm.onSucces();
            }
        }
    });
    }
    public void delete_plan(Plan plan){
        repository.deletePlanned(plan);
    }
    public void dataArrived(PlannesMeal plannesMeal){
        Log.d(TAG, "dataArrivasdasdasdadadsed: "+plannesMeal.meals.size());
        comm.onSucces(plannesMeal.meals);
    }


}
