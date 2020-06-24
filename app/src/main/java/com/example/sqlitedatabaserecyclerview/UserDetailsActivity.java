package com.example.sqlitedatabaserecyclerview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetailsActivity extends AppCompatActivity {

    private ImageView userImage;
    private TextView usernName;
    private TextView userEmail;
    private String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("User Details");

        userImage = findViewById(R.id.userImage);
        usernName = findViewById(R.id.name_text);
        userEmail = findViewById(R.id.email_text);

        Intent getData = getIntent();
        name = getData.getExtras().getString("name");
        email = getData.getExtras().getString("email");

        usernName.setText(name);
        userEmail.setText(email);

    }
}
