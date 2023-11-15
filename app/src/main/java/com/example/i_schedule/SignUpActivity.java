package com.example.i_schedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    // Declare your UI components
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize your UI components
        editTextFirstName = findViewById(R.id.firstnameEditText);
        editTextLastName = findViewById(R.id.lastnameEditText);
        editTextEmail = findViewById(R.id.emailEditText);
        editTextPassword = findViewById(R.id.passwordEditText);
        buttonSignUp = findViewById(R.id.signupButton);

        // Set click listener for the sign up button
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // You can add validation for these inputs before proceeding

        // For now, we'll just display a toast message
        String message = "First Name: " + firstName + "\nLast Name: " + lastName + "\nEmail: " + email + "\nPassword: " + password;
        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_LONG).show();

        // Here, you would usually send these details to your server to perform the actual sign up

        // After sign up, navigate to HomePageActivity
        Intent intent = new Intent(SignUpActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();  // Optional: if you want SignUpActivity to be removed from the back stack
    }
}