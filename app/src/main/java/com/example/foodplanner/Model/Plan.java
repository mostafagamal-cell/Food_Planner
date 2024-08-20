package com.example.foodplanner.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Plans", primaryKeys = {"Day", "time","type"})
public class Plan extends Meal {
    @NonNull
    public String Day;
    @NonNull
    public String time;
    @NonNull
    public String type;
}