package com.ackincolor.cloudito.ui.authentification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import android.content.Intent;
import android.os.Bundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.fragment.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import android.os.Bundle;
import com.ackincolor.cloudito.R;
import java.util.concurrent.Executors;

public class BiometricActivity extends AppCompatActivity {

    private static final String TAG = BiometricActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biometric);

        Executor newExecutor = Executors.newSingleThreadExecutor();

        FragmentActivity activity = this;

        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override

//Callback when fatal error//

            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                } else {
                    Log.d(TAG, "Une erreur a été rencontrée");
                }
            }

//onAuthenticationSucceeded is called when a fingerprint is matched successfully//

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                //implement the redirection here
                Log.d(TAG, "Authentification OK");


            }

//Callback when no match between id and what the user identify//

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.d(TAG, "Authentification non valide");
            }
        });

//Create the BiometricPrompt instance//

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()

                .setTitle("Choissisez un moyen de vous authentifier")
                .setNegativeButtonText("Annuler")
                .build();

//Listener to use the button and launch the authentication process//

        findViewById(R.id.launchAuthentication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBiometricPrompt.authenticate(promptInfo);
            }
        });

    }
}