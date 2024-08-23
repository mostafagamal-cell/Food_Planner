package com.example.foodplanner.FavouriteScreen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.Adapter.FavouriteAdpter;
import com.example.foodplanner.App;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.IfavouritePresenter;
import com.example.foodplanner.Util.InternetBroadcastReciver;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FragmentFavouriteScreenBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class FavouriteScreen extends Fragment implements MyClickListner,IfavouritePresenter.COMM {
    FragmentFavouriteScreenBinding binding;
    FavouriteAdpter adapter;
    IfavouritePresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding = FragmentFavouriteScreenBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = FavouritePresenter.getInstance(this);
        adapter=new FavouriteAdpter(this);
            App.Login_State.observe(getViewLifecycleOwner(), login_state -> {
                if (login_state .equals(App.Logged_in)){
                       presenter.readDatafromDB().observe(getViewLifecycleOwner(), meals -> {
                        Meals meals1 =new Meals();
                        meals1.meals=new ArrayList<>(meals);
                        adapter.update(meals1);
                        binding.FaveortaieREC.setAdapter(adapter);
                    });
                }else{
                    Toast.makeText(requireContext(), getString(R.string.youarenotlogin), Toast.LENGTH_SHORT).show();
                }
            });

        binding.asyncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user",null);
                if (email==null){
                    Toast.makeText(getContext(), getString(R.string.youarenotlogin), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (InternetBroadcastReciver.booleanMutableLiveData.getValue()) {
                    presenter.saveOnClould(email, adapter.meals);
                }else{
                    Toast.makeText(FavouriteScreen.this.getContext(), FavouriteScreen.this.getString(R.string.notconnected), Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user",null);
                if (email==null){
                    Toast.makeText(getContext(), getString(R.string.youarenotlogin), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (InternetBroadcastReciver.booleanMutableLiveData.getValue()) {
                    presenter.readOnClould(email);
                }else{
                    Toast.makeText(FavouriteScreen.this.getContext(), FavouriteScreen.this.getString(R.string.notconnected), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void OnClick(Meal meal) {
        FavouriteScreenDirections.ActionFavouriteScreenToMealItemScreen action = FavouriteScreenDirections.actionFavouriteScreenToMealItemScreen(meal);
        NavHostFragment.findNavController(this).navigate(action);
    }
    @Override
    public void OnClickDelte(Meal meal) {
        adapter.remove(meal);
        presenter.deletFavourite(meal);
    }

    @Override
    public void onDataArrive(Meals meals) {
        Log.d("14488444", "onDataArrive: "+meals.meals.size());
        for (int m = 0; m < meals.meals.size(); m++) {
            presenter.saveOnDB(meals.meals.get(m));
        }
    }
}