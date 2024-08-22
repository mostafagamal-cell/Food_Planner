package com.example.foodplanner;

import android.app.Application;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import androidx.lifecycle.MutableLiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.ImainPresenter;
import com.example.foodplanner.Util.InternetBroadcastReciver;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

public class App extends Application implements ImainPresenter {
     public static String Guest= "Guest";
     public  static String Logged_in = "Logged_in";
     public  static String not_Logged_in="not_Logged_in";
     public static Boolean naigateback=false;
    MyRepository repository;
     public  static  MutableLiveData<String> Login_State;
    @Override
    public void onCreate() {
        super.onCreate();
        Login_State = new MutableLiveData<>();
        SharedPreferences preferences = this.getSharedPreferences("user",MODE_PRIVATE);
        String test =preferences.getString("user",null);
        if (test==null){
            Login_State.setValue(not_Logged_in);
        }else{
            Login_State.setValue(Logged_in);
        }
        repository=MyRepository.getInstance(this,this,"N/A");
        repository.getcatigorys();
        repository.getListOfarea();
        repository.getListOfingredients();
        for (int i = 0; i < 26; i++) {
           char a=(char) ('a'+i);
           repository.getMealByletter(a+"");
        }
        FirebaseApp.initializeApp(this);
        registerReceiver(new InternetBroadcastReciver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        MyRepository.getInstance(new ImainPresenter() {}, this, "N/A").getListOfcategories();
    }
}
