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
import com.example.wl.answer.adapter.MessageAdapter;
import com.example.wl.answer.listener.RVItemClickListener;
import com.example.wl.answer.model.MessageInfo;

import java.util.ArrayList;

/**
 * Created by wanglin on 17-3-10.
 */

public class MessageFragment extends BaseFragment {
    private ArrayList<MessageInfo> messageInfoList = new ArrayList<>();
    private Context mContext;

    public static MessageFragment newInstance() {

        Bundle args = new Bundle();

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(View view,Context context) {
        mContext = context;
        RecyclerView messageRv = (RecyclerView) view.findViewById(R.id.recyclerview);
        messageRv.setLayoutManager(new LinearLayoutManager(mContext));
        messageRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL)); //设置分割线
        MessageAdapter adapter = new MessageAdapter(messageInfoList);
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

        for (int i = 0; i < 10; i++) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setId(""+i);
            messageInfo.setName("小船儿" + i);
            messageInfo.setTime(i + "" + i + ":" + i + "" + i);
            messageInfo.setContent("内容" + i + " 不以物喜，不以己悲，己所不欲勿施于人");
            messageInfoList.add(messageInfo);
        }
    }
}
