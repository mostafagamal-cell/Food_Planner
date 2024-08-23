package com.example.foodplanner.auth;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.foodplanner.App;
import com.example.foodplanner.MealScreen.MealScreenActivity;
import com.example.foodplanner.Presenters.LoginPresenter;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.IauthPresenter;
import com.example.foodplanner.databinding.FragmentLogin2Binding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

public class LoginFragment extends Fragment implements IauthPresenter.IauthComm {
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
        if (savedInstanceState!=null){
            binding.email.getEditText().setText(savedInstanceState.getString("eeee"));
            binding.password.getEditText().setText(savedInstanceState.getString("pppp"));
        }

        presenter=LoginPresenter.getInstance(this,this);
        App.Login_State.observe(getViewLifecycleOwner(),state->{
            if (!App.naigateback) {
                if (state.equals(App.Logged_in)) {
                    startActivity(new Intent(getContext(), MealScreenActivity.class));
                    requireActivity().finish();
                }
                if (state.equals(App.Guest)) {
                    startActivity(new Intent(getContext(), MealScreenActivity.class));
                    requireActivity().finish();
                }
            }else{
                if (state.equals(App.Logged_in)) {
                    App.naigateback=false;
                    requireActivity().finish();
                }
            }
        });
        binding.GustTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.Login_State.setValue(App.Guest);
            }
        });
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
        binding.createaccounttextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigate(R.id.createAccountFragment);
            }
        });
    }

    private void setupLoginButton() {
        binding.loginaccountbutton.setOnClickListener(view -> {

            String email = binding.email.getEditText().getText().toString();
            String password = binding.password.getEditText().getText().toString();
            hide();
            presenter.loginWithEmailAndPassword(email,password);
        });
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginWithGoogle();
            }
        });
    }
    @Override
    public void Sucess(String email) {
        Log.i("hhhhhhhhhhhhhhhhhhhh",email);
        Toast.makeText(getContext(), getContext().getString(R.string.welcom)  , Toast.LENGTH_SHORT).show();
        requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit().putString("user",email).apply();
        App.Login_State.setValue(App.Logged_in);
        show();
    }

    @Override
    public void Error(String error) {
        Log.i("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", "errorPasswordValidation: "+error);
        if (Objects.equals(error, getString(R.string.Authfild))) Toast.makeText(getContext(), getString(R.string.Authfild) , Toast.LENGTH_SHORT).show();
        else Toast.makeText(getContext(), error , Toast.LENGTH_SHORT).show();
        show();
    }

    @Override
    public void errorEmailValidation(String error) {
        Log.i("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", "errorPasswordValidation: "+error);

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
        Log.i("eeeeeeeeeeeeeeeeeeee","dddddddddddd");
        binding.loginaccountbutton.setVisibility(View.INVISIBLE);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.loginaccountbutton.setEnabled(false);
    }
    private void show(){
        Log.i("eeeeeeeeeeeeeeeeeeee","hhhhhhhhhh");
        binding.loginaccountbutton.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.loginaccountbutton.setEnabled(true);
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
      String email=   binding.email.getEditText().getText().toString();
      String pass= binding.password.getEditText().getText().toString();
        outState.putString("eeee",email);
        outState.putString("pppp",pass);

    }
}
