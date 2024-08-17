package com.example.foodplanner.ItemScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Adapter.ItemCatigoryRec;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.databinding.FragmentCatigoryItemBinding;

public class CatigoryMeals extends Fragment implements IcatigortItemPresenter.IcatigortItemComm {
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
            String name= args.getName();
            Log.i("zxcczxczxccxz",name);
            presenter.loadMeals(name);
            ((AppCompatActivity)requireActivity()).getSupportActionBar().setTitle(name);

        }
        rec= new ItemCatigoryRec();
        binding.recyclerView.setAdapter(rec);
    }

    @Override
    public void onDataArrived(Meals categories) {
        rec.setcontect(categories);
    }
}