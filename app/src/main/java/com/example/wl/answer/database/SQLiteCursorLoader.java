package com.example.wl.answer.database;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

/**
 * Created by wanglin on 17-3-21.
 */

public abstract class SQLiteCursorLoader extends AsyncTaskLoader<Cursor> {
    private final static String TAG = "============>";
    private Cursor mCursor;

    public SQLiteCursorLoader(Context context) {
        super(context);
    }

    protected abstract Cursor loadCursor();

    @Override
    public Cursor loadInBackground() {
        Log.i(TAG, "loadInBackground: ");
        Cursor cursor = loadCursor();
        if (cursor != null) {
            cursor.getCount();
        }
        return cursor;
    }

    @Override
    public void deliverResult(Cursor data) {
        Log.i(TAG, "deliverResult: ");
        Cursor oldCursor = mCursor;
        mCursor = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
        if (oldCursor != null && oldCursor != data && !oldCursor.isClosed()) {
            oldCursor.close();
        }
    }

    @Override
    protected void onStartLoading() {
        Log.i(TAG, "onStartLoading: ");
        if (mCursor != null) {
            deliverResult(mCursor);
        }
        if (takeContentChanged() || mCursor == null) {
            forceLoad();
        }
    }

    @Override
    public void onCanceled(Cursor data) {
        Log.i(TAG, "onCanceled: ");
        if (data != null && !data.isClosed()) {
            data.close();
        }
    }

    @Override
    protected void onStopLoading() {
        Log.i(TAG, "onStopLoading: ");
        cancelLoad();
    }

    @Override
    protected void onReset() {
        Log.i(TAG, "onReset: ");
        super.onReset();
        onStopLoading();
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
        mCursor = null;
    }
}
