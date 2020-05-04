package com.ackincolor.cloudito.ui.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.entities.Credentials;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_send;
    private EditText et_pseudo, et_email, et_password, et_password2;
    private TextView tvUserLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                if (validate()) {

                }

                //Credentials c = new Credentials();
                // if(password.equalsIgnoreCase(password2)) {
                //   c.setLogin(email);
                // c.setName(pseudo);
                //c.setPwd(password);
                //}
            }
        });

        tvUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, AuthentificationFragment.class));

            }
        });
    }




    private void setupUIViews() {
        btn_send = (Button) findViewById(R.id.btn_send);
        et_pseudo = (EditText) findViewById(R.id.et_pseudo);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password2 = (EditText) findViewById(R.id.et_password2);
        tvUserLogin = (TextView) findViewById(R.id.tvUserLogin);
    }

    private Boolean validate() {
        Boolean result =false;

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
        if (pseudo.isEmpty()&& password.isEmpty() && email.isEmpty()){
            Toast.makeText(this, "please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
        }

}


