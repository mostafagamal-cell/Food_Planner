package com.example.foodplanner.Util;

public interface IauthPresenter {
    public interface IloginPresenter {
        void Login(String email, String password);
        int checkEmail(String email);
        int checkPassword(String password);
        interface Icommuncate{
            void Sucess();
            void Error(String error);
            void errorEmailValidation(String error);
            void errorPasswordValidation(String error);

        }
    }
}
