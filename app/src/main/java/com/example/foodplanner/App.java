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

public class App extends Application {
     public static String Guest= "Guest";
     public  static String Logged_in = "Logged_in";
     public  static String not_Logged_in="not_Logged_in";
     public  static  MutableLiveData<String> Login_State;
     public  static  MutableLiveData<Boolean>Connection_State;
    public static ArrayList<Meal>all_meals=new ArrayList<>();
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
        FirebaseApp.initializeApp(this);
        registerReceiver(new InternetBroadcastReciver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        MyRepository.getInstance(new ImainPresenter() {}, this, "N/A").getListOfcategories();
    }
}
