package com.example.i_schedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePageActivity extends AppCompatActivity {

    // Declare your UI components
    private RecyclerView serverListRecyclerView;
    private FloatingActionButton addServerButton;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_list);

        // Initialize your UI components
        serverListRecyclerView = findViewById(R.id.serverListRecyclerView);
        addServerButton = findViewById(R.id.addServerButton);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        // Set up RecyclerView with data
        setupRecyclerView();

        // Set up BottomNavigationView
        setupBottomNavigation();
    }

    private void setupRecyclerView() {

    }
    private void setupBottomNavigation() {
        // Set item selected listener
        bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                default:
                    return false;
            }
        });
    }
}