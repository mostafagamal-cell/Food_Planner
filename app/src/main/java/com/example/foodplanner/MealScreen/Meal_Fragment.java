package com.example.foodplanner.MealScreen;

import static com.example.foodplanner.Util.InternetBroadcastReciver.booleanMutableLiveData;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Adapter.CategoriesAdapter;
import com.example.foodplanner.Adapter.CuntriesAdpter;
import com.example.foodplanner.Adapter.InteREc;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Presenters.MealScreenPresenter;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.IfragmentMealComm;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FragmentMealBinding;


public class Meal_Fragment extends Fragment implements IfragmentMealComm, MyClickListner {
    FragmentMealBinding binding;
    MealScreenPresenter presenter;
    CategoriesAdapter adapter;
    InteREc InAdpter;
    CuntriesAdpter cuntriesAdpter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding=FragmentMealBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void OnClick(Meal meal) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=MealScreenPresenter.getinstance(this);
        booleanMutableLiveData.observe(this.requireActivity(),e->{
        if (e) {
            presenter.getRandommeal();
            presenter.getcatigorys();
            presenter.getListOfingredients();
            presenter.getListOfarea();
        } });
        Log.i("eeeeeeeaaaaaaaaeeeeeeeee", ((AppCompatActivity)requireActivity()).getSupportActionBar().getTitle()+"");
    }

    @Override
    public void onDataArrivedRandomaMeal(Meals meals) {
        Log.i("dddddddddd",meals.meals.get(0).strMealThumb);
        Glide.with(this)
                .load(meals.meals.get(0).strMealThumb)
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background) .error(R.drawable.ic_launcher_foreground).override(100, 100))
                .into(binding.myImage);
        binding.itemtext.setText(meals.meals.get(0).strMeal);
        binding.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Meal_FragmentDirections.ActionMealFragment2ToMealItemScreen actionMealFragmentToMealItemScreen = Meal_FragmentDirections.actionMealFragment2ToMealItemScreen(meals.meals.get(0));
            NavHostFragment.findNavController(Meal_Fragment.this).navigate(actionMealFragmentToMealItemScreen);
            }
        });
    }


    @Override
    public void onDataArrivedCategories(Categories categories) {
        adapter=new CategoriesAdapter(this);
        binding.recyclerView4.setAdapter(adapter);
        adapter.setCategories(categories);
    }

    @Override
    public void onDataArrivedIngredients(Ingradiants meals) {
        InAdpter= new InteREc(this);
        binding.recyclerView5.setAdapter(InAdpter);
        InAdpter.setIngradiants(meals);
    }

    @Override
    public void onDataArrivedCountry(Countries meals) {
     cuntriesAdpter= new CuntriesAdpter(this);
     cuntriesAdpter.setCuntries(meals);
     binding.recyclerView6.setAdapter(cuntriesAdpter);
    }
    @Override
    public void OnClick(Category v) {

    Meal_FragmentDirections.ActionMealFragment2ToCatigoryMeals actionMealFragmentToCatigoryItem = Meal_FragmentDirections.actionMealFragment2ToCatigoryMeals(v);
    NavHostFragment.findNavController(this).navigate(actionMealFragmentToCatigoryItem);
    }

    @Override
    public void OnClick(String area,int type) {
      if (type==0) {

          NavHostFragment.findNavController(this).navigate(Meal_FragmentDirections.actionMealFragment2ToAreaItemFragment(area));
      }else{

          NavHostFragment.findNavController(this).navigate( Meal_FragmentDirections.actionMealFragment2ToIngItemFrag(area));
      }
    }
}