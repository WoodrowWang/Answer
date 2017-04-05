package com.example.wl.answer.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wl.answer.R;
import com.example.wl.answer.activity.ChatActivity;
import com.example.wl.answer.adapter.MessageRecyclerViewAdapter;
import com.example.wl.answer.listener.RVItemClickListener;
import com.example.wl.answer.listener.RVItemTouchListener;
import com.example.wl.answer.model.MessageInfo;

import java.util.ArrayList;

/**
 * Created by wanglin on 17-3-10.
 */

public class FragmentMessage extends BaseFragment {
    private ArrayList<MessageInfo> messageInfoList = new ArrayList<>();
    private Context mContext;

    public static FragmentMessage newInstance() {

        Bundle args = new Bundle();

        FragmentMessage fragment = new FragmentMessage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(View view,Context context) {
        mContext = context;
        RecyclerView messageRv = (RecyclerView) view.findViewById(R.id.recyclerview);
        messageRv.setLayoutManager(new LinearLayoutManager(mContext));
        messageRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL)); //设置分割线
        MessageRecyclerViewAdapter adapter = new MessageRecyclerViewAdapter(messageInfoList);
        messageRv.setAdapter(adapter);
        adapter.setItemClickListener(new RVItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, ChatActivity.class);
                intent.putExtra("friendId",messageInfoList.get(position).getId());
                intent.putExtra("friendName",messageInfoList.get(position).getName());
                startActivity(intent);
            }
        });
        RVItemTouchListener itemTouchListener = new RVItemTouchListener(context);
        itemTouchListener.setClickListener(new RVItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, ChatActivity.class);
                intent.putExtra("friendId",messageInfoList.get(position).getId());
                intent.putExtra("friendName",messageInfoList.get(position).getName());
                startActivity(intent);
            }
        });

        for (int i = 0; i < 10; i++) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setId(""+i);
            messageInfo.setName("name" + i);
            messageInfo.setTime(i + "" + i + ":" + i + "" + i);
            messageInfo.setContent("content" + i + "hakjhafkajdhkjhfanvduvhaeuiavdjavjkd");
            messageInfoList.add(messageInfo);
        }
    }
}
