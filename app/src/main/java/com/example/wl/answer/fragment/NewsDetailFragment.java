package com.example.wl.answer.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.wl.answer.R;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class NewsDetailFragment extends BaseFragment {

    private String mTitle;
    public static NewsDetailFragment newInstance(Intent intent) {

        Bundle args = new Bundle();
        args.putString("title",intent.getStringExtra("newsTitle"));
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getResId() {
        return R.layout.activity_news_details;
    }

    @Override
    protected void init(View view, Context context) {
        if (getArguments() != null) {
            mTitle = getArguments().getString("title");
        }
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(mTitle);
    }
}
