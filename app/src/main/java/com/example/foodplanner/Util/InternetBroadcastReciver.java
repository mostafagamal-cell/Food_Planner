package com.example.foodplanner.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodplanner.R;

public class InternetBroadcastReciver extends BroadcastReceiver {
    public static MutableLiveData<Boolean>booleanMutableLiveData=new MutableLiveData<>(false);
    @Override
    public void onReceive(Context context, Intent intent) {
    ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo network=connectivityManager.getActiveNetworkInfo();
    if (network!=null&&network.isConnectedOrConnecting()){
        Toast.makeText(context, context.getString(R.string.connected), Toast.LENGTH_SHORT).show();
        booleanMutableLiveData.setValue(true);
    }else{
        Toast.makeText(context, context.getString(R.string.notconnected), Toast.LENGTH_SHORT).show();
        booleanMutableLiveData.setValue(false);
    }
    }
}
