package com.example.foodplanner.Presenters;

import static com.example.foodplanner.Util.Utilits.emailPattern;
import static com.example.foodplanner.Util.Utilits.passwordPattern;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.foodplanner.R;
import com.example.foodplanner.Util.IauthPresenter;
import com.example.foodplanner.auth.AuthActivity;
import com.example.foodplanner.auth.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements IauthPresenter.IloginPresenter {
    private LoginFragment  context;
    private static LoginPresenter presenter;
    private final FirebaseAuth firebaseAuth;
    private  IauthPresenter.IloginPresenter.Icommuncate icommuncate;
    private LoginPresenter(){
        firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void Login(String email, String password) {
        int res=checkEmail(email);
        int res2=checkPassword(password);
        if (res<0 || res2<0)
            return;
        boolean isEmailValid = email.matches(emailPattern);
        boolean isPasswordValid = password.matches(passwordPattern);
        if (!isEmailValid) {
            icommuncate.errorEmailValidation(context.getString(R.string.email_is_invalid));
        }
        if (!isPasswordValid) {
            icommuncate.errorEmailValidation(context.getString(R.string.password_is_invalid));
        }
        if (!isEmailValid||!isPasswordValid)
            return;

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(context.requireActivity(),task -> {
            Log.d("xxxxxxxxxxxxxxx", "signInWithEmail:onComplete:" + task.isSuccessful());
          if (task.isSuccessful()){
              icommuncate.Sucess(email);
          }else{
              icommuncate.Error(task.getException().getMessage());
          }
        });

    }

    @Override
    public int checkEmail(String email) {
        if (email.trim().isEmpty()) {
            icommuncate.errorEmailValidation(context.getString(R.string.email_is_empty));
            return -1;
        }
        return 0;
    }

    @Override
    public int checkPassword(String password) {
        if (password.trim().isEmpty()) {
            icommuncate.errorPasswordValidation(context.getString(R.string.password_is_empty));
            return -1;
        }
        return 0;

    }

    public static synchronized  LoginPresenter getInstance(LoginFragment context, IauthPresenter.IloginPresenter.Icommuncate icommuncate) {

         if (presenter == null) {
            presenter= new LoginPresenter();
        }
        presenter.context=context;
        presenter.icommuncate= icommuncate;
        return presenter;
    }

}
