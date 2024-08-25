package com.example.foodplanner.AreaItem;


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

import com.example.foodplanner.Adapter.CuntriesAdpter;
import com.example.foodplanner.Adapter.ItemAreaRec;
import com.example.foodplanner.DataSourse.LocalDataSourse;
import com.example.foodplanner.DataSourse.RemoteDataSourse;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.IareaFrag;
import com.example.foodplanner.Util.IareaMealsPresenter;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FragmentAreaItemBinding;


public class AreaItemFragment extends Fragment implements IareaFrag, MyClickListner {
    ItemAreaRec adpter;
    FragmentAreaItemBinding binding;
    AreaPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding=FragmentAreaItemBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=new AreaPresenter(this, MyRepository.getInstance(LocalDataSourse.getInstance(this.getActivity().getApplication()), RemoteDataSourse.getInstance()));
        if (getArguments()!=null){
            String area=AreaItemFragmentArgs.fromBundle(getArguments()).getArea();
            ((AppCompatActivity)requireActivity()).getSupportActionBar().setTitle(area);
            Log.d("sddddddddddddddddddddddddddddddddddd", "onViewCreated: "+area);
            presenter.loadMeals(area);
        }
    }

    @Override
    public void onSucess(Meals categories) {
        adpter= new ItemAreaRec(this);
        adpter.setcontect(categories);
        binding.myrecycler.setAdapter(adpter);
    }

    @Override
    public void OnClick(Meal meal) {
        NavHostFragment.findNavController(this).navigate(AreaItemFragmentDirections.actionAreaItemFragmentToMealItemScreen(meal));
    }
    @Override
    public void fail(String Message) {
        Toast.makeText(this.getActivity(), Message, Toast.LENGTH_SHORT).show();
    }
}