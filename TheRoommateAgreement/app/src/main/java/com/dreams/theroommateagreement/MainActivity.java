package com.dreams.theroommateagreement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 1;

    private FirebaseAuth traFirebaseAuth;
    private FirebaseAuth.AuthStateListener traAuthStateListener;
    private String traUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase components
        traFirebaseAuth = FirebaseAuth.getInstance();

        traAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    // Toast.makeText(MainActivity.this, "You're now signed in. Welcome to FriendlyChat.", Toast.LENGTH_SHORT).show();
                    onSignedInInitialize(user.getDisplayName());
                } else {
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
                    );

                    // User is signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                    onSignedOutCleanup();
                }
            }
        };
    }

    private void onSignedInInitialize(String userName) {
        traUserName = userName;
        attachDatabaseReadListener();
    }

    private void onSignedOutCleanup() {
        detachDatabaseReadListener();
    }

    private void attachDatabaseReadListener() {

    }

    private void detachDatabaseReadListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        traFirebaseAuth.addAuthStateListener(traAuthStateListener);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (traAuthStateListener != null) {
            traFirebaseAuth.removeAuthStateListener(traAuthStateListener);
        }
        detachDatabaseReadListener();
    }
}
