package com.ackincolor.cloudito.ui.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ackincolor.cloudito.AuthenticationService.AuthenticationLoginAndroidService;
import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.entities.AuthStatus;
import com.ackincolor.cloudito.entities.Credentials;

import java.util.UUID;

public class AuthentificationFragment extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info, Register;
    private Button Login;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_authentification);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfotextView);
        Login = (Button)findViewById(R.id.btnLogin);
        Register = (TextView)findViewById(R.id.tvRegister);

        Info.setText("Number of attempts remaining: 5");

        Login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        }));

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthentificationFragment.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

    }

    private void validate(String userName, String userPassword) {
        AuthenticationLoginAndroidService authenticationLoginAndroidService = new AuthenticationLoginAndroidService(this);
        Credentials credentials = new Credentials();
        credentials.setLogin(userName);
        credentials.setPwd(userPassword);
        authenticationLoginAndroidService.authenticateLogin(credentials);
    }

    public void failAuthent(){
        counter --;
        Info.setText("Number of attempts remaining : " + String.valueOf(counter));
        if(counter == 0){
            Login.setEnabled(false);
        }
    }

    public void successAuthent(AuthStatus authStatus){
        Intent intent = new Intent(AuthentificationFragment.this, GoogleAuthFragment.class);
        startActivity(intent);
    }


}
