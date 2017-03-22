package com.example.wl.answer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by wanglin on 17-3-15.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ChatLog.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "chatLog";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(friend_id,content,date)");
        Log.i(TAG, "onCreate: database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
