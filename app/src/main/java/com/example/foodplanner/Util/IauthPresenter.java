package com.example.foodplanner.Util;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

public interface IauthPresenter {
     interface Icommuncate{
        void Sucess(String email);
        void Error(String error);
        void errorEmailValidation(String error);
        void errorPasswordValidation(String error);
     }
    interface IcommuncateCreate extends  Icommuncate{
        void errorPasswordValidationConvermation(String error);
    }

     interface Ivalidate{
         int checkEmail(String email);
         int checkPassword(String password);
         void handleResult(Task<GoogleSignInAccount> accountTask);
     }

     interface IloginPresenter extends Ivalidate {
        void loginWithGoogle();
        void loginWithEmailAndPassword(String email, String password);
    }

    interface IsignupPresenter extends Ivalidate  {
        void signup(String email, String password, String confirmPassword);
        int confermationPassword(String Password, String confirmPassword);
        int errorPasswordValidationConvermation(String Password, String confirmPassword);
    }
}
