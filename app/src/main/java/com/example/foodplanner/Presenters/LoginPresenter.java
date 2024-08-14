package com.example.foodplanner.Presenters;

import static com.example.foodplanner.Util.Utilits.emailPattern;
import static com.example.foodplanner.Util.Utilits.passwordPattern;

import android.content.Intent;
import android.util.Log;

import com.example.foodplanner.R;
import com.example.foodplanner.Util.IauthPresenter;
import com.example.foodplanner.auth.LoginFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginPresenter implements IauthPresenter.IloginPresenter {
    protected  LoginFragment  context;
    private GoogleApiClient  googleApiClient;

    private static LoginPresenter presenter;
    private final FirebaseAuth firebaseAuth;
    private  IauthPresenter.Icommuncate icommuncate;
    private LoginPresenter(LoginFragment context, LoginFragment icommuncate){
        firebaseAuth=FirebaseAuth.getInstance();
            GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getString(R.string.id_token))
                    .requestEmail()
                    .build();
            googleApiClient=new GoogleApiClient.Builder(context.requireActivity())
                    .enableAutoManage(context.requireActivity(),connectionResult -> {
                        icommuncate.Error(connectionResult.getErrorMessage());
                    })
                    .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                    .build();
    }
    public void loginWithGoogle(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        context.startActivityForResult(intent, 1);
    }
    @Override
    public void loginWithEmailAndPassword(String email, String password) {
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
            icommuncate.errorPasswordValidation(context.getString(R.string.password_is_invalid));
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

    @Override
    public void handleResult(Task<GoogleSignInAccount> accountTask) {
        try {
            GoogleSignInAccount account = accountTask.getResult(ApiException.class);
            addToFireBase(account.getIdToken());
        } catch (Exception e) {
            Log.w("LoginFragment", "signInResult:failed code=" + e.getMessage());
            icommuncate.Error(context.getString(R.string.Connection_Fail));
        }
    }

    public static synchronized  LoginPresenter getInstance(LoginFragment context, LoginFragment icommuncate) {

         if (presenter == null) {
            presenter= new LoginPresenter(context,icommuncate);
        }
        presenter.context=context;
        presenter.icommuncate= icommuncate;
        return presenter;
    }
    private void addToFireBase(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(context.requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        // Notify your presenter or update your UI
                        icommuncate.Sucess(user.getEmail());
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("LoginFragment", "signInWithCredential:failure", task.getException());
                        icommuncate.Error("Authentication Failed.");
                    }
                });
    }

}
