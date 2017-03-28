package com.example.wl.answer.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wl.answer.R;
import com.example.wl.answer.adapter.ChatRecyclerViewAdapter;
import com.example.wl.answer.database.ChatLogManager;
import com.example.wl.answer.database.SQLiteCursorLoader;
import com.example.wl.answer.model.ChatText;

import java.util.ArrayList;

/**
 * Created by wanglin on 17-3-10.
 */

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "=================>";
    private ArrayList<ChatText> mChatTexts;
    private String mText = "";
    private ChatRecyclerViewAdapter mAdapter;
    private EditText mEditText;
    private RecyclerView mRecyclerView;
    private ChatLogManager mChatLogManager;
    private String mFriendId;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Button mChangeBt;
    private Button mVoiceBt;
    private LinearLayout mTextEnter;
    private LoaderManager mLoaderManager;
    private boolean isInputMethodOpened, isFirst;
    private CharSequence mString;
    private int mSeletedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setItemTouchListener(new ChatRecyclerViewAdapter.OnItemTouchListener() {
            @Override
            public void onItemLongClick(int position) {
                makeToast("long clicked: " + position);
            }

            @Override
            public void onItemTouch() {

            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, int position) {
                getMenuInflater().inflate(R.menu.chat_context_menu, menu);
                mString = mChatTexts.get(position).getText();
                mSeletedPosition = position;
            }
        });
        mEditText.addTextChangedListener(textWatcher);
        mEditText.setOnClickListener(this);
        mEditText.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        mEditText.getWindowVisibleDisplayFrame(r);
                        int screenHeight = mEditText.getRootView()
                                .getHeight();
                        int heightDifference = screenHeight - (r.bottom);
                        if (heightDifference > 200) {
                            isInputMethodOpened = true;
                            if (mChatTexts.size() >= 1)
                                mRecyclerView.scrollToPosition(mChatTexts.size() - 1);
                        }
                    }

                });
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (isInputMethodOpened) {
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ChatActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    isInputMethodOpened = false;
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLoaderManager.restartLoader(0, null, chatLogCursorLoaderCallbacks);
            }
        });
        mSwipeRefreshLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ChatActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return true;
            }
        });
        mChangeBt.setOnClickListener(this);
    }

    private void init() {
        isInputMethodOpened = false;
        isFirst = true;
        ImageButton send = (ImageButton) findViewById(R.id.chat_send);
        send.setOnClickListener(this);

        Intent intent = getIntent();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(intent.getStringExtra("friendName"));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mFriendId = intent.getStringExtra("friendId");

        mChatTexts = new ArrayList<>();
        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0, null, chatLogCursorLoaderCallbacks);

        mChatLogManager = new ChatLogManager(this);
        mChangeBt = (Button) findViewById(R.id.button_change);
        mVoiceBt = (Button) findViewById(R.id.voice);
        mTextEnter = (LinearLayout) findViewById(R.id.text_enter_layout);
        mAdapter = new ChatRecyclerViewAdapter(mChatTexts);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_chat);
        mEditText = (EditText) findViewById(R.id.chat_edit);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.chat_refresh);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mText = s.toString();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_send:
                if (!mText.equals("")) {
                    ChatText chatText = new ChatText();
                    chatText.setFriendId(mFriendId);
                    chatText.setText(mText);
                    chatText.setType(ChatText.TYPE_OWN);
                    chatText.setDate(System.currentTimeMillis());
                    Log.i(TAG, "onClick: " + System.currentTimeMillis());
                    mChatTexts.add(chatText);
                    mAdapter.notifyItemInserted(mChatTexts.size() - 1);
                    mChatLogManager.addChatLog(chatText);
                    mEditText.setText("");
                }
                break;
            case R.id.button_change:
                if (mChangeBt.getText().equals("语音输入")) {
                    mVoiceBt.setVisibility(View.VISIBLE);
                    mTextEnter.setVisibility(View.GONE);
                    mChangeBt.setText("文字输入");
                } else {
                    mVoiceBt.setVisibility(View.GONE);
                    mTextEnter.setVisibility(View.VISIBLE);
                    mChangeBt.setText("语音输入");
                }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.cm_copy:
                ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText("",mString));
                break;
            case R.id.cm_delete:
                mChatLogManager.deleteChatLog(new String[]{String.valueOf(mChatTexts.get(mSeletedPosition).getLogId())});
                mChatTexts.remove(mSeletedPosition);
                mAdapter.notifyDataSetChanged();
                break;

        }
        return true;
    }

    private static class ChatLogCursorLoader extends SQLiteCursorLoader {
        private String friendId;
        private int index;

        private ChatLogCursorLoader(Context context, String friendId, int index) {
            super(context);
            this.friendId = friendId;
            this.index = index;
        }

        @Override
        protected Cursor loadCursor() {
            return (new ChatLogManager(getContext())).getChatLogCursor(friendId, index);
        }
    }

    private LoaderManager.LoaderCallbacks<Cursor> chatLogCursorLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Log.i(TAG, "onCreateLoader: ");
            return new ChatLogCursorLoader(ChatActivity.this, mFriendId, mChatTexts.size());
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            Log.i(TAG, "onLoadFinished: ");
            if (data.moveToLast()) {
                ArrayList<ChatText> contents = new ArrayList<>();
                do {
                    ChatText chatText = new ChatText();
                    chatText.setText(data.getString(data.getColumnIndex("content")));
                    chatText.setDate(data.getLong(data.getColumnIndex("date")));
                    chatText.setLogId(data.getLong(data.getColumnIndex("_id")));
                    contents.add(chatText);
                } while (data.moveToPrevious());
                mChatTexts.addAll(0, contents);
                mAdapter.notifyDataSetChanged();
                if (isFirst) {
                    if (mChatTexts.size() >= 1) {
                        mRecyclerView.scrollToPosition(mChatTexts.size() - 1);
                    }
                    isFirst = false;
                }
            } else {
                if (!isFirst) {
                    makeToast("no more");
                }
            }
            mSwipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            Log.i(TAG, "onLoaderReset: ");
            mChatTexts = null;
        }
    };

    private void makeToast(String s) {
        Toast.makeText(ChatActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChatLogManager.close();

    }
}
