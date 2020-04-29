package com.ackincolor.cloudito.ui.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.entities.Credentials;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_send;
    private EditText et_pseudo, et_email, et_password, et_password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_send = (Button) findViewById(R.id.btn_send);
        et_pseudo = (EditText) findViewById(R.id.et_pseudo);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password2 = (EditText) findViewById(R.id.et_password2);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo = et_pseudo.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String password2 = et_password2.getText().toString().trim();
                Credentials c = new Credentials();
                if(password.equalsIgnoreCase(password2)) {
                    c.setLogin(email);
                    c.setName(pseudo);
                    c.setPwd(password);
                }
            }
        });
    }
}
