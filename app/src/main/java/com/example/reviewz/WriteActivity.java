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

public class WriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_write);
        getSupportActionBar().hide();

        revDb dbHelper2 = new revDb(WriteActivity.this);
        SQLiteDatabase db2 = dbHelper2.getWritableDatabase();

        Button btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] columns = {"title", "language", "genre", "rating", "review"};
                EditText[] fields = {findViewById(R.id.editTitle), findViewById(R.id.editLanguage),
                                     findViewById(R.id.editGenre), findViewById(R.id.editRating), findViewById(R.id.editReview)};

                ContentValues values = new ContentValues();
                for(int i=0;i<5;i++){
                    if(i == 3){
                        values.put(columns[i], Double.parseDouble(fields[i].getText().toString()));
                    }
                    else{
                        values.put(columns[i], fields[i].getText().toString());
                    }
                }
                long newRowId = db2.insert("userreview", null, values);

                Toast.makeText(WriteActivity.this, "Thanks for your review!", Toast.LENGTH_SHORT).show();
                for(int i=0;i<5;i++){
                    fields[i].getText().clear();
                }
            }
        });

        Button btnRead = findViewById(R.id.btnRead);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WriteActivity.this, ReadActivity.class);
                startActivity(intent);
            }
        });
    }
}