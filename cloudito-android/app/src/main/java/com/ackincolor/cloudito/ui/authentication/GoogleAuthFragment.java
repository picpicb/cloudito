package com.ackincolor.cloudito.ui.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ackincolor.cloudito.MainActivity;
import com.ackincolor.cloudito.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GoogleAuthFragment extends AppCompatActivity {

    private EditText AuthPswd2;
    private TextView Welcome;
    private Button Login2;
    private static final String TAG = BiometricActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_auth);

        AuthPswd2 = (EditText)findViewById(R.id.editTextAct2);
        Welcome = (TextView)findViewById(R.id.textViewAct2);
        Login2 = (Button)findViewById(R.id.buttonAct2);


        //Start of Biometric part

        Executor newExecutor = Executors.newSingleThreadExecutor();

        FragmentActivity activity = this;

        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override

//Callback when fatal error//

            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                } else {
                    Log.d(TAG, "Une erreur a été rencontree");
                }
            }

//onAuthenticationSucceeded is called when a fingerprint is matched successfully//

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);


                Log.d(TAG, "Authentification OK");
                //implement the redirection here

                startActivity(new Intent(getApplicationContext(), MainActivity.class));

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


        Login2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (validate(AuthPswd2.getText().toString()) = true) {
                if (true == true){
                    myBiometricPrompt.authenticate(promptInfo);
                }

            }
        }
        ));


    }

    /*
    private boolean validate(String googleAuthPassword) {
        if((googleAuthPassword.equals("Admin"))) {
           return true;
        }else{
            return false;
            }
        }
    }

     */


}
