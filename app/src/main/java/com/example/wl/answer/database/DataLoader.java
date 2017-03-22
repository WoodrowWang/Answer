package com.example.wl.answer.database;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.wl.answer.model.ChatText;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.example.wl.answer.database.DatabaseHelper.TABLE_NAME;

/**
 * Created by wanglin on 17-3-20.
 */

public class DataLoader extends AsyncTaskLoader<ArrayList<ChatText>> {

    private int index;
    private String friendId;
    private DatabaseHelper databaseHelper;
    public DataLoader(Context context,int index,String friendId) {
        super(context);
        databaseHelper = new DatabaseHelper(context);
        this.friendId = friendId;
        this.index = index;
    }

    @Override
    public ArrayList<ChatText> loadInBackground() {
        String limit = index * 20 + ",20";
        ArrayList<ChatText> contents = new ArrayList<>();
        Cursor cursor = databaseHelper.getWritableDatabase().query(TABLE_NAME, new String[]{"content"},
                "friend_id = ?", new String[]{friendId}, null, null, "date desc", limit);
        if (cursor.moveToLast()) {
            do {
                Log.i(TAG, "getContents: " + cursor.getString(cursor.getColumnIndex("content")) + "  limit: " + limit);
                ChatText chatText = new ChatText();
                chatText.setText(cursor.getString(cursor.getColumnIndex("content")));
                contents.add(chatText);
            } while (cursor.moveToPrevious());
        }
        cursor.close();
        return contents;
    }
}
