package com.example.reviewz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        revDb dbHelper1 = new revDb(SignupActivity.this);
        SQLiteDatabase db1 = dbHelper1.getWritableDatabase();

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] columns = {"name", "email", "username", "password"};
                EditText[] fields = {findViewById(R.id.editName), findViewById(R.id.editEmail),
                                     findViewById(R.id.editUsername), findViewById(R.id.editPassword)};

                ContentValues values = new ContentValues();
                for(int i=0;i<4;i++){
                    values.put(columns[i], fields[i].getText().toString());
                }
                long newRowId = db1.insert("user", null, values);

                Toast.makeText(SignupActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}