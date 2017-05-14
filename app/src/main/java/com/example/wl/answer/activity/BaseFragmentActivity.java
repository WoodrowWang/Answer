package com.example.wl.answer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.wl.answer.R;

/**
 * Created by wanglin on 17-4-5.
 */

public abstract class BaseFragmentActivity extends AppCompatActivity {
    protected abstract Fragment getFragment();
    public void init(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_base);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, getFragment())
                .commit();
    }
}
