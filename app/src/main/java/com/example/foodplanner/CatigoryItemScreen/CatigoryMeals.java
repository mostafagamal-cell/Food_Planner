package com.example.foodplanner.CatigoryItemScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Adapter.ItemCatigoryRec;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Util.IcatigortItemPresenter;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FragmentCatigoryItemBinding;

public class CatigoryMeals extends Fragment implements IcatigortItemPresenter.IcatigortItemComm, MyClickListner {
    FragmentCatigoryItemBinding binding;
    IcatigortItemPresenter presenter;
    ItemCatigoryRec rec;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding=FragmentCatigoryItemBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       presenter=CatigoryItemPresenter.getInstance(this,this.requireActivity().getApplication());
        if (getArguments()!=null){
            CatigoryMealsArgs args= CatigoryMealsArgs.fromBundle(getArguments());
            Category meal = args.getCat();
            Log.i("zxcczxczxccxz",meal.strCategory);
            presenter.loadMeals(meal.strCategory);
            ((AppCompatActivity)requireActivity()).getSupportActionBar().setTitle(meal.strCategory);

        }
        rec= new ItemCatigoryRec(this);
        binding.recyclerView.setAdapter(rec);
    }

    @Override
    public void OnClick(Meal meal) {
        NavHostFragment.findNavController(this).navigate(CatigoryMealsDirections.actionCatigoryMealsToMealItemScreen(meal));
         }

    @Override
    public void onDataArrived(Meals categories) {
        rec.setcontect(categories);
    }
}