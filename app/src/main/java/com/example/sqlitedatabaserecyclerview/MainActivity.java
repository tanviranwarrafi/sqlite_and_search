package com.example.sqlitedatabaserecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText;
    private Button insertBtn;
    private Button showDataBtn;
    private String name, email;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.name_insert);
        emailEditText = findViewById(R.id.email_insert);
        insertBtn = findViewById(R.id.insert_button);
        showDataBtn = findViewById(R.id.gotoShowActivity);

        showDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showActivityIntent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(showActivityIntent);
            }
        });

        databaseHelper = new DatabaseHelper(this);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }

    private void insertData() {
        name = nameEditText.getText().toString();
        email = emailEditText.getText().toString();
        long id = databaseHelper.insertData(name,email);

        Toast.makeText(this, name +" is inserted successfully", Toast.LENGTH_SHORT).show();
    }
}
