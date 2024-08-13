package com.example.foodplanner;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.google.firebase.FirebaseApp;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        registerReceiver(new InternetBroadcastReciver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }
}
