package com.example.wl.answer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.wl.answer.model.ChatText;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by wanglin on 17-3-15.
 */

public class ChatLogManager {
    private static final String TABLE_NAME = "chatLog";
    private static final String FRIEND_ID = "friend_id";
    private static final String CONTENT = "content";
    private static final String DATE = "date";
    private static final String TYPE = "type";

    private DatabaseHelper mDatabaseHelper;

    public ChatLogManager(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public void addChatLog(ChatText chatText) {
        ContentValues cv = new ContentValues();
        cv.put(FRIEND_ID, chatText.getFriendId());
        cv.put(CONTENT, chatText.getText());
        cv.put(DATE, chatText.getDate());
//        cv.put(TYPE,chatText.getType());
        mDatabaseHelper.getWritableDatabase().insert(TABLE_NAME, null, cv);
    }

    public ArrayList<ChatText> getContents(String friendId, int index) {
        Log.i("============>", "getContents: ");
        String limit = index * 20 + ",20";
        ArrayList<ChatText> contents = new ArrayList<>();
        Cursor cursor = mDatabaseHelper.getWritableDatabase().query(TABLE_NAME, new String[]{"content"},
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

    public Cursor getChatLogCursor(String friendId, int index){
        String limit = index * 20 + ",20";
        ArrayList<ChatText> contents = new ArrayList<>();
        return mDatabaseHelper.getWritableDatabase().query(TABLE_NAME, new String[]{"content"},
                "friend_id = ?", new String[]{friendId}, null, null, "date asc", limit);
    }

    public void deleteChatLog(String[] ids){
        mDatabaseHelper.getWritableDatabase().delete(TABLE_NAME,"id = ?",ids);
    }

    public void close() {
        mDatabaseHelper.close();
    }
}
