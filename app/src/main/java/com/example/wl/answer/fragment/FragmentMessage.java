package com.example.wl.answer.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wl.answer.adapter.MessageRecyclerViewAdapter;
import com.example.wl.answer.adapter.MessageRecyclerViewAdapter.OnItemClickListener;
import com.example.wl.answer.R;
import com.example.wl.answer.activity.ChatActivity;
import com.example.wl.answer.model.MessageInfo;

import java.util.ArrayList;

/**
 * Created by wanglin on 17-3-10.
 */

public class FragmentMessage extends Fragment {

    private Context mContext;
    private ArrayList<MessageInfo> messageInfoList = new ArrayList<>();

    public static FragmentMessage newInstance() {

        Bundle args = new Bundle();

        FragmentMessage fragment = new FragmentMessage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        RecyclerView messageRv = (RecyclerView) view.findViewById(R.id.recyclerview_message);
        messageRv.setLayoutManager(new LinearLayoutManager(mContext));
        messageRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL)); //设置分割线
        MessageRecyclerViewAdapter adapter = new MessageRecyclerViewAdapter(messageInfoList);
        messageRv.setAdapter(adapter);
        adapter.setItemClickListener(new OnItemClickListener() {
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
        return view;
    }


}
