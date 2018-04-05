package com.dreams.theroommateagreement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import static com.dreams.theroommateagreement.SignupActivity.passwordLayout;

/**
 * Created by abhishekvjoshi on 2/3/18.
 */

public class SignupValidations {

    private static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    public static boolean isRegisted(@NonNull Task<AuthResult> task, final Context context) {
        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
            Toast.makeText(context, "This email address is already registered!",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static boolean isValidPassword(final String password) {

        if (!password.matches(PASSWORD_PATTERN)) {
            passwordLayout.setError("Password must satisfy the following conditions: minimum length 8, 1 digit, 1 Uppercase character, 1 lower case character, 1 special character.");
            return false;
        }
        else {
            passwordLayout.setErrorEnabled(false);
        }
        return true;
    }

    public static boolean isValidPasswordConfirmation(final String confirmationPassword,
                                                      final String password) {
        return password.equals(confirmationPassword);
    }

    public static boolean isValidEmail(final String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());

    }
}
