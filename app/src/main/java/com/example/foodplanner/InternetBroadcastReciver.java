package com.example.foodplanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.widget.Toast;

public class InternetBroadcastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo network=connectivityManager.getActiveNetworkInfo();
    if (network!=null&&network.isConnectedOrConnecting()){
        Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(context, "not connected", Toast.LENGTH_SHORT).show();
    }
    }
}
