package com.example.foodplanner.Util;

public interface IauthPresenter {
    public interface IloginPresenter {
        void Login(String email, String password);
        interface Icommuncate{
            void Sucess();
            void Error(String error);
        }
    }
}
