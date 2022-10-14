package com.example.quizmobileappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private Button clickMe;
    private EditText inputUsername;
    private EditText inputPassword;
    private EditText inputEmail;
    private EditText inputFirstName;
    private EditText inputLastName;
    private String usernameString;
    private String passwordString;
    private String emailString;
    private String firstNameString;
    private String lastNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        clickMe = findViewById(R.id.register_btn);

        inputUsername = findViewById(R.id.username_field);
        inputPassword = findViewById(R.id.password_field);
        inputEmail = findViewById(R.id.email_field);
        inputFirstName = findViewById(R.id.first_name_field);
        inputLastName = findViewById(R.id.last_name_field);

        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernameString = inputUsername.getText().toString();
                passwordString = inputPassword.getText().toString();
                emailString = inputEmail.getText().toString();
                firstNameString = inputFirstName.getText().toString();
                lastNameString = inputLastName.getText().toString();

                Intent intent = new Intent(view.getContext(), Main.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", "admin");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}