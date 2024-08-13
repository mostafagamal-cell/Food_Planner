package com.example.foodplanner.NetWork;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofite {
    private static volatile Retrofit retrofit;
    private static volatile Iretrofit iretrofit;
    public static synchronized Iretrofit getInstance() {

                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                 iretrofit=retrofit.create(Iretrofit.class);
        }
        return iretrofit;
    }
}
