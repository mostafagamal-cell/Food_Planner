package com.example.foodplanner.Presenters;

import static com.example.foodplanner.Util.Utilits.emailPattern;
import static com.example.foodplanner.Util.Utilits.passwordPattern;

import android.content.Intent;
import android.util.Log;

import com.example.foodplanner.R;
import com.example.foodplanner.Util.IauthPresenter;
import com.example.foodplanner.auth.CreateAccountFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class CreateAccountPresenter  implements IauthPresenter.IsignupPresenter {
    private CreateAccountFragment context;
    private GoogleSignInClient mGoogleSignInClient;
    private static CreateAccountPresenter presenter;
    private final FirebaseAuth   firebaseAuth;
    private IauthPresenter.IauthCommCreate icommuncate;
    private CreateAccountPresenter(CreateAccountFragment context, IauthPresenter.IauthCommCreate icommuncate){
        firebaseAuth =  FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.id_token))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context.requireActivity(), gso);
    }

    @Override
    public void signup(String email, String password,String passwordConvermation) {
        int res=checkEmail(email);
        int res2=checkPassword(password);
        int res3=checkPassword2(passwordConvermation);
        if (res<0 || res2<0||res3<0)
            return;
        int res4=confermationPassword(password,passwordConvermation);
        if (res4<0)
            return;
        boolean isEmailValid = email.matches(emailPattern);
        boolean isPasswordValid = password.matches(passwordPattern);
        if (!isEmailValid) {
            icommuncate.errorEmailValidation(context.getString(R.string.email_is_invalid));
        }
        if (!isPasswordValid) {
            icommuncate.errorPasswordValidation(context.getString(R.string.password_is_invalid));
        }
        if(!isEmailValid||!isPasswordValid)
            return;
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(context.requireActivity(),task -> {
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
        if (!Password.equals(confirmPassword)) {
            icommuncate.errorPasswordValidationConvermation(context.getString(R.string.password_does_not_match));
            return -1;
        }
        return 0;
    }

    @Override
    public int errorPasswordValidationConvermation(String Password, String confirmPassword) {
        if (confirmPassword.trim().isEmpty()) {
            icommuncate.errorPasswordValidation(context.getString(R.string.password_does_not_match));
            icommuncate.errorPasswordValidation(context.getString(R.string.password_does_not_match));
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
    public void signupWithGoogle(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        context.startActivityForResult(signInIntent, 1);
    }

    @Override
    public int checkPassword(String password) {
        Log.i("zzzzzzzzzzzzzzzzzzzzzzzz", "checkPassword: ");
        if (password.trim().isEmpty()) {
            icommuncate.errorPasswordValidation(context.getString(R.string.password_is_empty));
            return -1;
        }
        return 0;

    }
    public int checkPassword2(String password) {
        Log.i("zzzzzzzzzzzzzzzzzzzzzzzz", "checkPassword: ");
        if (password.trim().isEmpty()) {
            icommuncate.errorPasswordValidationConvermation(context.getString(R.string.password_is_empty));
            return -1;
        }
        return 0;
    }

    @Override
    public void handleResult(Task<GoogleSignInAccount> accountTask) {
        try {
            GoogleSignInAccount account = accountTask.getResult(ApiException.class);

            // Signed in successfully, authenticate with Firebase
            addToFireBase(account.getIdToken());
        } catch (Exception e) {
            Log.w("LoginFragment", "signInResult:failed code=" + e.getMessage());
            icommuncate.Error(context.getString(R.string.Connection_Fail));
        }
    }

    public static synchronized CreateAccountPresenter getInstance(CreateAccountFragment context, IauthPresenter.IauthCommCreate icommuncate) {
       if (presenter==null){
           presenter = new CreateAccountPresenter(context,icommuncate);
       }
        presenter.icommuncate=icommuncate;
        presenter.context=context;
        return presenter ;
    }
    private void addToFireBase(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(context.requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        // Notify  your presenter or update your UI
                        icommuncate.Sucess(user.getEmail());
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("LoginFragment", "signInWithCredential:failure", task.getException());
                        icommuncate.Error("Authentication Failed.");
                    }
                });
    }

}
