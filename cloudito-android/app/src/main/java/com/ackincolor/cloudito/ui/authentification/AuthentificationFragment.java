package com.ackincolor.cloudito.ui.authentification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ackincolor.cloudito.R;

public class AuthentificationFragment extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
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

        Info.setText("Number of attempts remaining: 5");

        Login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        }));

    }

    private void validate(String userName, String userPassword) {
        if((userName.equals("Admin")) && (userPassword.equals("Admin"))) {
            Intent intent = new Intent(AuthentificationFragment.this, SecondActivity.class);
            startActivity(intent);
        }else{
            counter --;

            Info.setText("Number of attempts remaining : " + String.valueOf(counter));

            if(counter == 0){
                Login.setEnabled(false);
            }
        }
    }


}
