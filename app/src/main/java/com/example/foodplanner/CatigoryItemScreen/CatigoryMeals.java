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
import android.widget.Toast;

import com.example.foodplanner.Adapter.ItemCatigoryRec;
import com.example.foodplanner.DataSourse.LocalDataSourse;
import com.example.foodplanner.DataSourse.RemoteDataSourse;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IcatigortItemFrag;
import com.example.foodplanner.Util.IcatigortItemPresenter;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FragmentCatigoryItemBinding;

public class CatigoryMeals extends Fragment implements IcatigortItemFrag, MyClickListner {
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
       presenter=new CatigoryItemPresenter(this, MyRepository.getInstance(LocalDataSourse.getInstance(this.getActivity().getApplication()), RemoteDataSourse.getInstance()));

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
    public void onScuess(Meals categories) {
        rec.setcontect(categories);

    }

    @Override
    public void onFail(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}