package com.example.i_schedule;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.i_schedule.models.User;
import com.example.i_schedule.api.UserApi;
import com.example.i_schedule.api.RetrofitClient;
import android.util.Log; // for logging the errors
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference the button from the XML layout
        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton = findViewById(R.id.signupButton);

        // Set an OnClickListener for the button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On button click, perform login
                loginUser();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //ont button click, perform signup
                navigateToSignUpPage();
            }
        });
    }
    private void navigateToSignUpPage() {
        // Create an Intent to start the SignUpActivity
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);

        // Start the activity
        startActivity(intent);
    }
    private void loginUser() {
        // Get user input from EditText fields
        EditText emailField = findViewById(R.id.emailEditText);
        EditText passwordField = findViewById(R.id.passwordEditText);

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        // Create an instance of User with user input
        User user = new User(email, password);

        // Make the API call using Retrofit
        UserApi api = RetrofitClient.getInstance().create(UserApi.class);
        Call<User> call = api.registerUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    // Handle the successful response, e.g.:
                    User returnedUser = response.body();
                    Log.d("API_SUCCESS", "User registered: " + returnedUser.getEmail());
                } else {
                    // Handle the error response, e.g.:
                    Log.e("API_ERROR", "Error code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Handle the failure, e.g.:
                Log.e("API_FAILURE", t.getMessage());
            }
        });
    }
}
