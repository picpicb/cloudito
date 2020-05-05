package com.ackincolor.cloudito.ui.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ackincolor.cloudito.AuthenticationService.AuthenticationRegisterAndroidService;
import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.entities.Credentials;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_send;
    private EditText et_pseudo, et_email, et_password, et_password2;
    private TextView tvUserLogin;
    private Credentials credential;
    private AuthenticationRegisterAndroidService authenticationRegisterAndroidService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
              validate();
            }
        });

        tvUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, AuthenticationFragment.class));

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

    private void validate() {
        String pseudo = et_pseudo.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String password2 = et_password2.getText().toString().trim();

        if (pseudo.isEmpty()|| password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Remplir TOUS les champs", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.equalsIgnoreCase(password2)) {
            credential = new Credentials();
            credential.setLogin(email);
            credential.setName(pseudo);
            credential.setPwd(password);
            authenticationRegisterAndroidService = new AuthenticationRegisterAndroidService(this);
            authenticationRegisterAndroidService.authenticateInscription(credential);
        }else{
            Toast.makeText(this, "Les mots de passe ne sont pas identiques", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void successRegister(String key){
        //Toast.makeText(this, "Voici votre clé goole AUTH", Toast.LENGTH_SHORT).show();
        String uri = "otpauth://totp/mail:" + credential.getLogin() + "?secret=" + key + "&issuer=Cloudito";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    public void failRegister(){
        Toast.makeText(this, "Mail déjà utilisé", Toast.LENGTH_SHORT).show();
    }

    public void badLogin(){
        Toast.makeText(this, "Saisir correctement le mail", Toast.LENGTH_SHORT).show();
    }
}


