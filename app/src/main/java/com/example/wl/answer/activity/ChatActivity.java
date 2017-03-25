package com.example.wl.answer.activity;

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

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = "=================>";
    private ArrayList<ChatText> chatTexts;
    private String text = "";
    private ChatRecyclerViewAdapter adapter;
    private EditText editText;
    private RecyclerView recyclerView;
    private ChatLogManager mChatLogManager;
    private String friendId;
    private SwipeRefreshLayout refreshLayout;
    private Button changeBt;
    private Button voiceTv;
    private LinearLayout textEnter;
    private int index;
    private LoaderManager mLoaderManager;
    private boolean isInputMethodOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnTouchListener(new ChatRecyclerViewAdapter.OnItemTouchListener() {
            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(ChatActivity.this,"long clicked:"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemTouch() {

            }
        });
        editText.addTextChangedListener(textWatcher);
        editText.setOnClickListener(this);
        editText.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        editText.getWindowVisibleDisplayFrame(r);
                        int screenHeight = editText.getRootView()
                                .getHeight();
                        int heightDifference = screenHeight - (r.bottom);
                        if (heightDifference > 200) {
                            isInputMethodOpened = true;
                            if (chatTexts.size() >= 1)
                                recyclerView.scrollToPosition(chatTexts.size() - 1);
                        }
                    }

                });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (isInputMethodOpened){
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ChatActivity.this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    isInputMethodOpened = false;
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                index++;
                mLoaderManager.restartLoader(0,null,chatLogCursorLoaderCallbacks);
            }
        });
        refreshLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ChatActivity.this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return true;
            }
        });
        changeBt.setOnClickListener(this);
    }

    private void init() {
        isInputMethodOpened = false;
        ImageButton send = (ImageButton) findViewById(R.id.chat_send);
        send.setOnClickListener(this);

        Intent intent = getIntent();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(intent.getStringExtra("friendName"));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        friendId = intent.getStringExtra("friendId");

        chatTexts = new ArrayList<>();
        index = 0;
        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0,null,chatLogCursorLoaderCallbacks);

        mChatLogManager = new ChatLogManager(this);
        changeBt = (Button) findViewById(R.id.button_change);
        voiceTv = (Button) findViewById(R.id.voice);
        textEnter = (LinearLayout) findViewById(R.id.text_enter_layout);
        adapter = new ChatRecyclerViewAdapter(chatTexts);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_chat);
        editText = (EditText) findViewById(R.id.chat_edit);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.chat_refresh);
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
            text = s.toString();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_send:
                if (!text.equals("")) {
                    ChatText chatText = new ChatText();
                    chatText.setFriendId(friendId);
                    chatText.setText(text);
                    chatText.setType(ChatText.TYPE_OWN);
                    chatText.setDate(System.currentTimeMillis());
                    chatTexts.add(chatText);
                    adapter.notifyItemInserted(chatTexts.size() - 1);
                    mChatLogManager.addChatLog(chatText);
                    editText.setText("");
                }
                break;
            case R.id.button_change:
                if (changeBt.getText().equals("语音输入")){
                    voiceTv.setVisibility(View.VISIBLE);
                    textEnter.setVisibility(View.GONE);
                    changeBt.setText("文字输入");
                }else{
                    voiceTv.setVisibility(View.GONE);
                    textEnter.setVisibility(View.VISIBLE);
                    changeBt.setText("语音输入");
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

    private static class ChatLogCursorLoader extends SQLiteCursorLoader{
        private String friendId;
        private int index;
        private ChatLogCursorLoader(Context context,String friendId,int index) {
            super(context);
            this.friendId = friendId;
            this.index = index;
        }

        @Override
        protected Cursor loadCursor() {
            return (new ChatLogManager(getContext())).getChatLogCursor(friendId,index);
        }
    }

    private LoaderManager.LoaderCallbacks<Cursor> chatLogCursorLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Log.i(TAG, "onCreateLoader: ");
            return new ChatLogCursorLoader(ChatActivity.this,friendId,index);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            Log.i(TAG, "onLoadFinished: ");
            if (data.moveToLast()) {
                ArrayList<ChatText> contents = new ArrayList<>();
                do {
                    ChatText chatText = new ChatText();
                    chatText.setText(data.getString(data.getColumnIndex("content")));
                    contents.add(chatText);
                } while (data.moveToPrevious());
                chatTexts.addAll(0,contents);
                adapter.notifyDataSetChanged();
                if (index == 0){
                    if (chatTexts.size() >= 1) {
                        recyclerView.scrollToPosition(chatTexts.size() - 1);
                    }
                }
            }else{
                if (index != 0) {
                    index--;
                    Toast.makeText(ChatActivity.this, "no more", Toast.LENGTH_SHORT).show();
                }
            }
            refreshLayout.setRefreshing(false);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            Log.i(TAG, "onLoaderReset: ");
            chatTexts = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChatLogManager.close();
    }
}
