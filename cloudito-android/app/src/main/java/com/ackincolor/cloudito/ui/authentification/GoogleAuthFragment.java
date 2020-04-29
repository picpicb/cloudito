package com.ackincolor.cloudito.ui.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ackincolor.cloudito.R;

public class GoogleAuthFragment extends AppCompatActivity {

    private EditText AuthPswd2;
    private TextView Welcome;
    private Button Login2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_auth);

        AuthPswd2 = (EditText)findViewById(R.id.editTextAct2);
        Welcome = (TextView)findViewById(R.id.textViewAct2);
        Login2 = (Button)findViewById(R.id.buttonAct2);

        /*
        Login2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(AuthPswd2.getText().toString());
            }
        }
        ));

         */
    }

    /*
    private void validate(String googleAuthPassword) {
        if((googleAuthPassword.equals("Admin"))) {
            Intent intent = new Intent(AuthentificationFragment.this, GoogleAuthFragment.class);
            startActivity(intent);
        }else{
            TextV
            }
        }
    }

     */


}
