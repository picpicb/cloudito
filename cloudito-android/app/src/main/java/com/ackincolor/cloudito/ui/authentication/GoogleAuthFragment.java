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
import android.widget.Toast;

import com.ackincolor.cloudito.AuthenticationService.AuthenticationOTPCodeAndroidService;
import com.ackincolor.cloudito.MainActivity;
import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.entities.AuthStatus;
import com.ackincolor.cloudito.entities.Credentials;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GoogleAuthFragment extends AppCompatActivity {

    private EditText authPswd2;
    private TextView welcome;
    private Button login2;
    private static final String TAG = "Authentication log :";
    private BiometricPrompt myBiometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    private AuthStatus authStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_auth);
        authStatus = (AuthStatus) getIntent().getSerializableExtra("AuthStatus");
        authPswd2 = (EditText)findViewById(R.id.editTextAct2);
        welcome = (TextView)findViewById(R.id.textViewAct2);
        login2 = (Button)findViewById(R.id.buttonAct2);

        //Start of Biometric part
        Executor newExecutor = Executors.newSingleThreadExecutor();
        FragmentActivity activity = this;
        myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                } else {
                    Log.d(TAG, "Une erreur a été rencontree");
                }
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.d(TAG, "Authentification OK");
                //implement the redirection here
               // Toast.makeText(getApplicationContext(), "Authentification réussie", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.d(TAG, "Authentification non valide");
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Choissisez un moyen de vous authentifier")
                .setNegativeButtonText("Annuler")
                .build();

        login2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(authPswd2.getText().toString());
            }
        }));
    }

    private void validate(String googleAuthPassword) {
        AuthenticationOTPCodeAndroidService authenticationOTPCodeAndroidService = new AuthenticationOTPCodeAndroidService(this);
        Credentials credentials = new Credentials();
        credentials.setUsrId(authStatus.getUsrId());
        credentials.setCode(googleAuthPassword);
        credentials.setUuid(authStatus.getUuid());
        authenticationOTPCodeAndroidService.authenticateOTPCode(credentials);
    }

    public void failAuthent(){
        //Toast.makeText(getApplicationContext(), "Echec de l'authentification", Toast.LENGTH_SHORT).show();

        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void successAuthent(AuthStatus authStatus){
        myBiometricPrompt.authenticate(promptInfo);
    }
}
