package com.example.foodplanner.Presenters;

import com.example.foodplanner.Util.IauthPresenter;

public class LoginPresenter implements IauthPresenter.IloginPresenter {
   public static LoginPresenter presenter;
    private LoginPresenter(){

    }

    @Override
    public void Login(String email, String password) {

    }

    public static synchronized  LoginPresenter getInstance() {
        if (presenter == null) {
            presenter= new LoginPresenter();
        }
        return presenter;
    }
}
