package com.example.foodplanner.MealPlane;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Repository.MyRepository;
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
    private ImealPlannerPresenter.Comm comm;
    MyRepository repository;
    public static final String TAG = "MealPlanePresenter";
    private MealPlanePresenter(Application application){
        repository=MyRepository.getInstance(this,application,TAG);
    }

    @Override
    public LiveData<List<Plan>> getPlans(String email) {
       return repository.getPlanned(email);
    }

    @Override
    public void insertPlan(Plan plan) {
        repository.insertPlanned(plan);
    }



    public static MealPlanePresenter getInstance(ImealPlannerPresenter.Comm comm , Application application) {
       if (presenter==null){
           presenter=new MealPlanePresenter(application);
       }
        presenter.comm=comm;
        return presenter ;
    }
    public void delete_plan(Plan plan){
        repository.deletePlanned(plan);
    }

}
