package com.example.foodplanner.auth;

import static com.example.foodplanner.Util.Utilits.emailPattern;
import static com.example.foodplanner.Util.Utilits.passwordPattern;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.Presenters.LoginPresenter;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.IauthPresenter;
import com.example.foodplanner.databinding.FragmentLogin2Binding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment implements IauthPresenter.IloginPresenter.Icommuncate {
    private FragmentLogin2Binding binding;


    LoginPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLogin2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=LoginPresenter.getInstance(this,this);
        setupFocusListeners();
        setupLoginButton();
    }

    private void setupFocusListeners() {
        binding.email.getEditText().setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                binding.email.setError(null);
                binding.erroremailtextview.setText("");
                binding.erroremailtextview.setVisibility(View.INVISIBLE);

            }
        });

        binding.password.getEditText().setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                binding.password.setError(null);
                binding.passworderrortextview.setText("");
                binding.passworderrortextview.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setupLoginButton() {
        binding.loginaccountbutton.setOnClickListener(view -> {

            String email = binding.email.getEditText().getText().toString();
            String password = binding.password.getEditText().getText().toString();
            presenter.Login(email,password);
            hide();
        });
    }



    @Override
    public void Sucess(String email) {
        Toast.makeText(getContext(), "Welcome "  , Toast.LENGTH_SHORT).show();
        requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit().putString("user",email).apply();
        show();
    }

    @Override
    public void Error(String error) {
        Toast.makeText(getContext(), "error  " + error , Toast.LENGTH_SHORT).show();
        show();
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
        binding.passworderrortextview.setVisibility(View.VISIBLE);
        binding.passworderrortextview.setText(error);
        binding.passworderrortextview.setTextColor(Color.RED);
    }

    private void hide(){
        binding.loginaccountbutton.setVisibility(View.INVISIBLE);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.loginaccountbutton.setEnabled(false);
    }
    private void show(){
        binding.loginaccountbutton.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.loginaccountbutton.setEnabled(true);
    }
}
