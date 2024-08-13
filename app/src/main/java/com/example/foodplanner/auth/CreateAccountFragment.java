package com.example.foodplanner.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.CreateaccountBinding;

public class CreateAccountFragment extends Fragment {
    CreateaccountBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=CreateaccountBinding.inflate(inflater);
        return binding.getRoot();
    }
}