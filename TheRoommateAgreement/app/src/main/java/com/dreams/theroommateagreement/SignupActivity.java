package com.dreams.theroommateagreement;

/**
 * Created by abhishekvjoshi on 1/26/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import org.w3c.dom.Text;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputPasswordConfirmation;
    private Button btnSignIn, btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    static TextInputLayout passwordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getInstances();

        inputPassword.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                SignupValidations.isValidPassword(inputPassword.getText().toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();
                final String passwordConfirmation = inputPasswordConfirmation.getText().toString().trim();

                if (!SignupValidations.isValidEmail(email)
                    || !SignupValidations.isValidPassword(password)
                    || !SignupValidations.isValidPasswordConfirmation(passwordConfirmation, password)) {
                    return;
                }




//                if (TextUtils.isEmpty(email)) {
//                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (TextUtils.isEmpty(password)) {
//                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                if (!SignupValidations.isRegisted(task, SignupActivity.this)) {
                                    Toast.makeText(SignupActivity.this, "Registration failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }
                                startActivity(new Intent(SignupActivity.this, SignupActivity.class));
                                finish();
                            } else {
                                startActivity(new Intent(SignupActivity.this, AdPostingActivity.class));
                                finish();
                            }
                        }
                    });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

//    private boolean isRegisted(@NonNull Task<AuthResult> task) {
//        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
//            Toast.makeText(SignupActivity.this, "This email address is already registered!",
//                    Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return false;
//    }
//
//    private void isValidPassword(final String password) {
//
//        if (!password.matches(PASSWORD_PATTERN)) {
//            passwordLayout.setError("Password must satisfy the following conditions: minimum length 8, 1 digit, 1 Uppercase character, 1 lower case character, 1 special character.");
//        }
//        else {
//            passwordLayout.setErrorEnabled(false);
//        }
//        return;
//    }
//
//    private boolean isValidPasswordConfirmation(final String confirmationPassword,
//                                             final String password) {
//        return password.equals(confirmationPassword);
//    }
//
//    private boolean isValidEmail(final String email) {
//        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
//
//    }

    private void getInstances() {
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputPasswordConfirmation = (EditText) findViewById(R.id.password_confirmation);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        final TextInputLayout emailLayout = (TextInputLayout) findViewById(R.id.password_layout);
        passwordLayout = (TextInputLayout) findViewById(R.id.password_layout);
    }

}