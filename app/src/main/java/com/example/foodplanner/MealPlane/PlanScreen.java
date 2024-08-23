package com.example.foodplanner.MealPlane;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.foodplanner.Adapter.PlanRec;
import com.example.foodplanner.App;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Model.PlannesMeal;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.InternetBroadcastReciver;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FavouriteitemBinding;
import com.example.foodplanner.databinding.FragmentPlanScreenBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PlanScreen extends Fragment implements ImealPlannerPresenter.Comm, MyClickListner, AdapterView.OnItemSelectedListener {
    FragmentPlanScreenBinding binding;
    PlanRec planRec;
    MealPlanePresenter presenter;
    private ArrayList<String> typs=new ArrayList<>();
    private ArrayList<String> days=new ArrayList<>();
    String email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlanScreenBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        typs.addAll(Arrays.asList(
                getString(R.string.Non),
                getString(R.string.breakfast),
                getString(R.string.Lunch),
                getString(R.string.Dinner)
        ));

        days.addAll(Arrays.asList(getString(R.string.Non), getString(R.string.Saturday), getString(R.string.Sunday), getString(R.string.Monday),
                getString(R.string.Tuesday), getString(R.string.Wednesday), getString(R.string.Thursday), getString(R.string.Friday)));

        presenter = MealPlanePresenter.getInstance(this);
         email = this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user", null);

        ArrayAdapter<String> typead = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, typs);
        ArrayAdapter<String> daysad = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, days);

        planRec = new PlanRec(this);
        planRec.filterDay(getString(R.string.Non));
        planRec.filterType(getString(R.string.Non));

        binding.dayspinner.setAdapter(daysad);
        binding.typespinner.setAdapter(typead);

        binding.dayspinner.setOnItemSelectedListener(this);
        binding.typespinner.setOnItemSelectedListener(this);

            if (presenter.f1==null||presenter.f2==null) {
                presenter.f1 = getString(R.string.Non);
                presenter.f2 = getString(R.string.Non);
            }
            binding.dayspinner.setSelection(typs.indexOf(presenter.f1));
            binding.typespinner.setSelection(days.indexOf(presenter.f2));
            App.Login_State.observe(this.requireActivity(),e->{
                email=this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user", null);
            });
            planRec.filterDay(presenter.f1);
            planRec.filterType(presenter.f2);
        if (Objects.equals(App.Login_State.getValue(), App.Logged_in)) {
            presenter.getPlans(email).observe(this.requireActivity(), plans -> {
                planRec.setupdate(plans);
                binding.planrec.setAdapter(planRec);
            });
        } else {
            Toast.makeText(this.getActivity(), getString(R.string.Pleaselogin), Toast.LENGTH_SHORT).show();
        }
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email!=null){
                    if (InternetBroadcastReciver.booleanMutableLiveData.getValue()) {
                        presenter.loadplane(email);
                    }else{
                        Toast.makeText(getContext(), getString(R.string.notconnected), Toast.LENGTH_SHORT).show();
                    }
            }else{
                Toast.makeText(PlanScreen.this.getActivity(), getString(R.string.Pleaselogin), Toast.LENGTH_SHORT).show();
            }

        }
        });
        binding.button6.setOnClickListener(v -> {
            if (email!=null){
                if (InternetBroadcastReciver.booleanMutableLiveData.getValue()) {
                    PlannesMeal plannesMeal = new PlannesMeal();
                    plannesMeal.meals = planRec.myplans;
                    presenter.saveplans(plannesMeal, email);
                }else {
                    Toast.makeText(getContext(), getString(R.string.notconnected), Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(PlanScreen.this.getActivity(), getString(R.string.youarenotlogin), Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onDataArrived(List<Plan> meal) {
        Log.i("eeeeeeeeeeeeeeedeefafaffa", meal.size()+"");
        planRec.setupdate(meal);
        binding.planrec.setAdapter(planRec);
        for (int i = 0; i < meal.size(); i++) {
            presenter.insertPlan(meal.get(i));
        }
    }

    @Override
    public void OnClick(Meal plan) {
        NavHostFragment.findNavController(this).navigate(PlanScreenDirections.actionPlanScreenToMealItemScreen(plan));
    }

    @Override
    public void OnClickDelte(Plan meal) {
        presenter.delete_plan(meal);
        Toast.makeText(this.getActivity(), getString(R.string.plandeleted), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == binding.dayspinner.getId()) {
            planRec.filterDay(days.get(i));
            presenter.f1=days.get(i);
        } else if (adapterView.getId() == binding.typespinner.getId()) {
            planRec.filterType(typs.get(i));
            presenter.f2=typs.get(i);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Do nothing
    }

}
