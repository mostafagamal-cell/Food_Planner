package com.example.foodplanner.MealItem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Adapter.ItemCatigoryRec;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;


public class MealItemScreen extends Fragment implements ImealScreenPresenter.ImealScreenComm {

    ItemCatigoryRec rec;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_item_screen, container, false);
    }

    @Override
    public void dataArrived(Meal meal) {

    }
}