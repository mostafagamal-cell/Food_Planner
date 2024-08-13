package com.example.foodplanner.auth;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.Presenters.CreateAccountPresenter;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.IauthPresenter;
import com.example.foodplanner.databinding.CreateaccountBinding;

public class CreateAccountFragment extends Fragment implements IauthPresenter.IcommuncateCreate {
    CreateaccountBinding binding;
    CreateAccountPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=CreateaccountBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter= CreateAccountPresenter.getInstance(this,this);
        binding.createaccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.email.getEditText().getText().toString();
                String password=binding.password.getEditText().getText().toString();
                String confirmpassword=binding.confermpassword.getEditText().getText().toString();
                presenter.signup(email,password,confirmpassword);
                hide();
            }
        });
    }

    @Override
    public void Sucess(String email) {

    }

    @Override
    public void errorEmailValidation(String error) {
        binding.email.setError(error);
        binding.erroremailtextview.setVisibility(View.VISIBLE);
        binding.erroremailtextview.setText(error);
        binding.erroremailtextview.setTextColor(Color.RED);
    }

    @Override
    public void errorPasswordValidation(String error) {
        binding.password.setError(error);
        binding.password.setVisibility(View.VISIBLE);
        binding.passworderrortextview.setText(error);
        binding.passworderrortextview.setTextColor(Color.RED);
    }

    private void hide(){
        binding.createaccountbutton.setVisibility(View.INVISIBLE);
        binding.progressBar2.setVisibility(View.VISIBLE);
        binding.createaccountbutton.setEnabled(false);
    }
    private void show(){
        binding.createaccountbutton.setVisibility(View.VISIBLE);
        binding.progressBar2.setVisibility(View.INVISIBLE);
        binding.createaccountbutton.setEnabled(true);
    }

    @Override
    public void errorPasswordValidationConvermation(String error) {
        binding.confermpassworderrortextview.setText(error);
        binding.confermpassword.setError(error);

    }
    public void Error(String error) {
        Toast.makeText(getContext(), "error  " + error , Toast.LENGTH_SHORT).show();
        show();
    }
}