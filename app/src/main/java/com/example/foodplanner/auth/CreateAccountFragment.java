package com.example.foodplanner.auth;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.Presenters.CreateAccountPresenter;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.IauthPresenter;
import com.example.foodplanner.databinding.CreateaccountBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

public class CreateAccountFragment extends Fragment implements IauthPresenter.IauthCommCreate {
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
        binding.email.getEditText().setOnFocusChangeListener((view2, hasFocus) -> {
            if (hasFocus) {
                binding.email.setError(null);
                binding.erroremailtextview.setText("");
                binding.erroremailtextview.setVisibility(View.INVISIBLE);
            }
        });

        binding.password.getEditText().setOnFocusChangeListener((view2, hasFocus) -> {
            if (hasFocus) {
                binding.password.setError(null);
                binding.passworderrortextview.setText("");
                binding.passworderrortextview.setVisibility(View.INVISIBLE);
            }
        });
        binding.confermpassword.getEditText().setOnFocusChangeListener((view2, hasFocus) -> {
            if (hasFocus) {
                binding.confermpassword.setError(null);
                binding.confermpassworderrortextview.setText("");
                binding.confermpassworderrortextview.setVisibility(View.INVISIBLE);
            }
        });
            binding.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.signupWithGoogle();
                }
            });

        binding.createaccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.email.getEditText().getText().toString();
                String password=binding.password.getEditText().getText().toString();
                String confirmpassword=binding.confermpassword.getEditText().getText().toString();
                hide();

                presenter.signup(email,password,confirmpassword);
            }
        });
    }

    @Override
    public void Sucess(String email) {
        Toast.makeText(getContext(), "Create Account Sucessful "  , Toast.LENGTH_SHORT).show();
        show();
        Navigation.findNavController(requireView()).navigateUp();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("xxxxxxxxxxxxxxxxxxxxxxxxxxxx",requestCode+"");
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            presenter.handleResult(task);
        }
    }

    @Override
    public void errorEmailValidation(String error) {
        binding.email.setError(error);
        binding.erroremailtextview.setVisibility(View.VISIBLE);
        binding.erroremailtextview.setText(error);
        binding.erroremailtextview.setTextColor(Color.RED);
        show();
    }

    @Override
    public void errorPasswordValidation(String error) {
        binding.password.setError(error);
        binding.passworderrortextview.setVisibility(View.VISIBLE);
        binding.passworderrortextview.setText(error);
        binding.passworderrortextview.setTextColor(Color.RED);
        show();
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
        Log.i("zzzzzzzzzzzzz", "errorPasswordValidationConvermation: ");
        binding.confermpassworderrortextview.setText(error);
        binding.confermpassword.setError(error);
        binding.confermpassworderrortextview.setVisibility(View.VISIBLE);
        binding.confermpassworderrortextview.setTextColor(Color.RED);
        if (error.equals(this.getString(R.string.password_does_not_match)))
             errorPasswordValidation(error);
        show();
    }
    public void Error(String error) {
        Toast.makeText(getContext(), "error  " + error , Toast.LENGTH_SHORT).show();
        show();
    }
}