package com.example.wl.answer.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.wl.answer.Adapter.ChatRecyclerViewAdapter;
import com.example.wl.answer.R;
import com.example.wl.answer.model.ChatText;

import java.util.ArrayList;

/**
 * Created by wanglin on 17-3-10.
 */

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<ChatText> chatTexts = new ArrayList<>();
    private String text = "";
    private ChatRecyclerViewAdapter adapter;
    private EditText editText;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(intent.getStringExtra("userName"));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new ChatRecyclerViewAdapter(chatTexts);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        editText = (EditText) findViewById(R.id.chat_edit);
        editText.addTextChangedListener(textWatcher);
        editText.setOnClickListener(this);

        ImageButton send = (ImageButton) findViewById(R.id.chat_send);
        send.setOnClickListener(this);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.edit_father);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        linearLayout.getWindowVisibleDisplayFrame(r);
                        int screenHeight = linearLayout.getRootView()
                                .getHeight();
                        int heightDifference = screenHeight - (r.bottom);
                        if (heightDifference > 200) {
                            recyclerView.scrollToPosition(chatTexts.size()-1);
                        }
                    }

                });
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
        switch (v.getId()){
            case R.id.chat_send:
                if (!text.equals("")) {
                    ChatText chatText = new ChatText();
                    chatText.setText(text);
                    chatText.setType(ChatText.TYPE_OWN);
                    chatTexts.add(chatText);
                    adapter.notifyItemInserted(chatTexts.size()-1);
                    editText.setText("");
                }
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ChatActivity.this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        return true;
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

}
