package com.example.foodplanner.MealPlane;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.Adapter.PlanRec;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FavouriteitemBinding;
import com.example.foodplanner.databinding.FragmentPlanScreenBinding;

import java.util.List;

public class PlanScreen extends Fragment implements ImealPlannerPresenter.Comm , MyClickListner {
    FragmentPlanScreenBinding binding;
    PlanRec planRec;
    MealPlanePresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding=FragmentPlanScreenBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=MealPlanePresenter.getInstance(this,this.getActivity().getApplication());
        String email=this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user",null );
        planRec=new PlanRec(this);
        if(email!=null)
                presenter.getPlans(email).observe(this.requireActivity(),plans -> {
                planRec.setcontent(plans);
                binding.planrec.setAdapter(planRec);
            });
        else
            Toast.makeText(this.getActivity(), "please login", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataArrived(List<Plan> meal) {
        planRec.setcontent(meal);
        binding.planrec.setAdapter(planRec);
    }


    @Override
    public void OnClick(Meal plan) {
        NavHostFragment.findNavController(this).navigate(PlanScreenDirections.actionPlanScreenToMealItemScreen(plan));
    }
}