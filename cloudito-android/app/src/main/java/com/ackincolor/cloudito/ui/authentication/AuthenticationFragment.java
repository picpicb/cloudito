package com.ackincolor.cloudito.ui.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ackincolor.cloudito.AuthenticationService.AuthenticationLoginAndroidService;
import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.entities.AuthStatus;
import com.ackincolor.cloudito.entities.Credentials;

public class AuthenticationFragment extends Fragment {

    private EditText name;
    private EditText password;
    private TextView info, register;
    private Button login;
    private int counter = 5;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.fragment_authentification);
        View root = inflater.inflate(R.layout.fragment_authentification, container, false);

        name = root.findViewById(R.id.etName);
        password = root.findViewById(R.id.etPassword);
        info = root.findViewById(R.id.tvInfotextView);
        login = root.findViewById(R.id.btnLogin);
        register = root.findViewById(R.id.tvRegister);

        info.setText("Number of attempts remaining: 5");

        login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(name.getText().toString(), password.getText().toString());
            }
        }));

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getContext(), RegisterActivity.class));
            }
        });

        return root;
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
        info.setText("Number of attempts remaining : " + String.valueOf(counter));
        if(counter == 0){
            login.setEnabled(false);
        }
    }

    public void successAuthent(AuthStatus authStatus){
        Intent intent = new Intent(getContext(), GoogleAuthFragment.class);
        intent.putExtra("AuthStatus",authStatus);
        startActivity(intent);
    }

}
