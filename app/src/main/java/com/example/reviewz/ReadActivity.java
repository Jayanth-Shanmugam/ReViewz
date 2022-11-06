package com.example.reviewz;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_read);
        Objects.requireNonNull(getSupportActionBar()).hide();

        revDb dbhelper5 = new revDb(ReadActivity.this);
        SQLiteDatabase db5 = dbhelper5.getReadableDatabase();

        Spinner movList = findViewById(R.id.listMovies);
        Cursor cursor1 = db5.rawQuery("select distinct(title) from userreview", null);
        List<String> titles = new ArrayList<>();
        while(cursor1.moveToNext()){
           String movTitle = cursor1.getString(0);
           titles.add(movTitle);
        }
        String[] titleArr = new String[titles.size()];
        titleArr = titles.toArray(titleArr);
        ArrayAdapter<String> adapter = new ArrayAdapter(ReadActivity.this, android.R.layout.simple_spinner_item, titleArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movList.setAdapter(adapter);

        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView[] userReviews = {findViewById(R.id.rev1), findViewById(R.id.rev2), findViewById(R.id.subrev2), findViewById(R.id.rev3),
                                          findViewById(R.id.subrev3), findViewById(R.id.rev4), findViewById(R.id.subrev4)};
                String searchKey = (String) movList.getSelectedItem();
                String[] reviews = new String[5];
                Cursor revCursor = db5.rawQuery("select * from userreview where title = ?", new String[]{searchKey});
                revCursor.moveToFirst();
                String review1 = revCursor.getString(0) + "\n" + revCursor.getString(1) + "\t\t\t\t" + revCursor.getString(2);
                userReviews[0].setText(review1);

                for(int i=1;i<=6;i+=2){
                    Double rating = revCursor.getDouble(3);
                    String movReview = revCursor.getString(4);
                    userReviews[i].setText(movReview);
                    userReviews[i+1].setText("Rating:  " + rating.toString() + " / 10");
                    revCursor.moveToNext();
                }
                //Toast.makeText(ReadActivity.this, "Selected " + searchKey, Toast.LENGTH_SHORT).show();
            }
        });


    }
}