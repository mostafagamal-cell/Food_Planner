package com.example.foodplanner.IngrItem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.Adapter.ItemIngrRec;
import com.example.foodplanner.DataSourse.LocalDataSourse;
import com.example.foodplanner.DataSourse.RemoteDataSourse;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.Iingfrage;
import com.example.foodplanner.Util.IingPresenter;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FragmentIngItemBinding;

public class IngItemFrag extends Fragment implements Iingfrage, MyClickListner {
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
            ((AppCompatActivity)requireActivity()).getSupportActionBar().setTitle(ing);
            presenter=new IngPresenter(this, MyRepository.getInstance(LocalDataSourse.getInstance(this.getActivity().getApplication()), RemoteDataSourse.getInstance()));

            presenter.loadMeals(ing);
        }
    }

    @Override
    public void OnClick(Meal meal) {
        IngItemFragDirections.ActionIngItemFragToMealItemScreen action = IngItemFragDirections.actionIngItemFragToMealItemScreen(meal);
        NavHostFragment.findNavController(this).navigate(action);

    }

    @Override
    public void onSuccess(Meals categories) {
        adapter= new ItemIngrRec(this);
        adapter.setcontect(categories);
        binding.eeeeef.setAdapter(adapter);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}