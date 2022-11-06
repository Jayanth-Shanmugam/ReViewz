package com.example.reviewz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        revDb dbHelper = new revDb(MainActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Button btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = findViewById(R.id.loginUser);
                EditText password = findViewById(R.id.loginPassword);
                String entUser = username.getText().toString();
                String entPass = password.getText().toString();

                String[] proj = {"username", "password"};
                String selection = "username = ? AND password = ?";
                String[] selection_args = {entUser, entPass};
                Cursor cursor = db.query("user", proj, selection, selection_args, null, null, null);

                if(cursor.moveToFirst()){
                    Toast.makeText(MainActivity.this, "Welcome " + entUser, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Oops! Wrong Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText username = findViewById(R.id.loginUser);
        EditText password = findViewById(R.id.loginPassword);
        username.getText().clear();
        password.getText().clear();
    }
}