package com.example.foodplanner.AboutFrag;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanner.R;

public class AboutFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
    TextView myview;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String email= requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user",null);
        myview=view.findViewById(R.id.emailtextviewonaboutfrag);
        if (email!=null)
         myview.setText(email);
        else
            myview.setText(getString(R.string.Guest));
    }
}