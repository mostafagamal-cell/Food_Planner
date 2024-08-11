package com.example.foodplanner;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(new InternetBroadcastReciver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }
}
