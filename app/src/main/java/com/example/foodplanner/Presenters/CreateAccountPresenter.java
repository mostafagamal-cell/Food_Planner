package com.example.foodplanner.Presenters;

import static com.example.foodplanner.Util.Utilits.emailPattern;
import static com.example.foodplanner.Util.Utilits.passwordPattern;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.R;
import com.example.foodplanner.Util.IauthPresenter;
import com.example.foodplanner.auth.CreateAccountFragment;
import com.example.foodplanner.auth.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountPresenter  implements IauthPresenter.IsignupPresenter {
    private CreateAccountFragment context;
    private static CreateAccountPresenter presenter;
    private final FirebaseAuth auth;
    private  IauthPresenter.IcommuncateCreate icommuncate;
    private CreateAccountPresenter(){
      auth =  FirebaseAuth.getInstance();
    }
    @Override
    public void signup(String email, String password,String passwordConvermation) {
        int res=checkEmail(email);
        int res2=checkPassword(password);
        int res3=checkPassword(passwordConvermation);
        int res4=confermationPassword(password,passwordConvermation);
        if (res<0 || res2<0||res3<0||res4<0)
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

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(context.requireActivity(),task -> {
            Log.d("xxxxxxxxxxxxxxx", "signInWithEmail:onComplete:" + task.isSuccessful());
            if (task.isSuccessful()){
                icommuncate.Sucess(email);
            }else{
                icommuncate.Error(task.getException().getMessage());
            }
        });
    }

    @Override
    public int confermationPassword(String Password, String confirmPassword) {
        if (Password.trim().isEmpty()) {
            icommuncate.errorPasswordValidationConvermation(context.getString(R.string.password_is_empty));
            return -1;
        }
        return 0;
    }

    @Override
    public int errorPasswordValidationConvermation(String Password, String confirmPassword) {
        if (confirmPassword.trim().isEmpty()) {
            icommuncate.errorPasswordValidation(context.getString(R.string.password_is_empty));
            return -1;
        }
        return 0;
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

    public static synchronized CreateAccountPresenter getInstance(CreateAccountFragment context,IauthPresenter.IcommuncateCreate icommuncate) {
       if (presenter==null){
           presenter = new CreateAccountPresenter();
       }
        presenter.icommuncate=icommuncate;
        presenter.context=context;
        return presenter ;
    }

}
