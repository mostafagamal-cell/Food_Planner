package com.example.foodplanner.Model;

import androidx.annotation.NonNull;
import androidx.room.*;



@Entity(tableName = "Plans", primaryKeys = {"Day", "time","type","email"})
public class Plan extends Meal {

    @NonNull
    public String Day;
    @NonNull
    public String time;
    @NonNull
    public String type;
}