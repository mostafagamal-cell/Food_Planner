package com.example.foodplanner.IngrItem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Adapter.ItemIngrRec;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.IingPresenter;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.CardItemBinding;
import com.example.foodplanner.databinding.FragmentIngItemBinding;

public class IngItemFrag extends Fragment implements IingPresenter.IareaMealsPresenterComm, MyClickListner {
    FragmentIngItemBinding binding;
    IingPresenter presenter;
    ItemIngrRec  adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentIngItemBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null){
            String ing=IngItemFragArgs.fromBundle(getArguments()).getIng();
            presenter=IngPresenter.getInstance(this,requireActivity().getApplication());
            presenter.loadMeals(ing);
        }
    }

    @Override
    public void OnClick(Meal meal) {
        IngItemFragDirections.ActionIngItemFragToMealItemScreen action = IngItemFragDirections.actionIngItemFragToMealItemScreen(meal);
        NavHostFragment.findNavController(this).navigate(action);

    }

    @Override
    public void onDataArrived(Meals categories) {
        adapter= new ItemIngrRec(this);
        adapter.setcontect(categories);
        binding.eeeeef.setAdapter(adapter);
    }
}