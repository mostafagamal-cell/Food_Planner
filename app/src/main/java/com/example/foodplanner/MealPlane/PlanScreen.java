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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.foodplanner.Adapter.PlanRec;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FavouriteitemBinding;
import com.example.foodplanner.databinding.FragmentPlanScreenBinding;

import java.util.List;

public class PlanScreen extends Fragment implements ImealPlannerPresenter.Comm, MyClickListner, AdapterView.OnItemSelectedListener {
    FragmentPlanScreenBinding binding;
    PlanRec planRec;
    MealPlanePresenter presenter;
    private String[] typs;
    private String[] days;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlanScreenBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the arrays now that the context is available
        typs = new String[]{getString(R.string.Non), getString(R.string.breakfast), getString(R.string.Lunch), getString(R.string.Dinner)};
        days = new String[]{getString(R.string.Non), getString(R.string.Saturday), getString(R.string.Sunday), getString(R.string.Monday),
                getString(R.string.Tuesday), getString(R.string.Wednesday), getString(R.string.Thursday), getString(R.string.Friday)};

        presenter = MealPlanePresenter.getInstance(this, this.getActivity().getApplication());
        String email = this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user", null);

        ArrayAdapter<String> typead = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, typs);
        ArrayAdapter<String> daysad = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, days);

        planRec = new PlanRec(this);
        planRec.filterDay(getString(R.string.Non));
        planRec.filterType(getString(R.string.Non));

        binding.dayspinner.setAdapter(daysad);
        binding.typespinner.setAdapter(typead);

        binding.dayspinner.setOnItemSelectedListener(this);
        binding.typespinner.setOnItemSelectedListener(this);
        planRec.filterDay(getString(R.string.Non));
        planRec.filterType(getString(R.string.Non));
        if (email != null) {
            presenter.getPlans(email).observe(this.requireActivity(), plans -> {
                planRec.setcontent(plans);
                binding.planrec.setAdapter(planRec);
            });
        } else {
            Toast.makeText(this.getActivity(), "Please login", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void OnClickDelte(Plan meal) {
        presenter.delete_plan(meal);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == binding.dayspinner.getId()) {
            planRec.filterDay(days[i]);
        } else if (adapterView.getId() == binding.typespinner.getId()) {
            planRec.filterType(typs[i]);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Do nothing
    }
}
