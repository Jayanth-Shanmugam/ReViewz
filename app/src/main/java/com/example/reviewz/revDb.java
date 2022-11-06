package com.example.reviewz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class revDb extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "review.db";

    public revDb(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_user_table = "CREATE TABLE user (name TEXT, email TEXT, username TEXT, password TEXT)";
        String create_user_review_table = "CREATE TABLE userreview (title TEXT, language TEXT, genre TEXT, rating REAL, review TEXT)";
        db.execSQL(create_user_table);
        db.execSQL(create_user_review_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS userreview");
        onCreate(db);
    }
}
