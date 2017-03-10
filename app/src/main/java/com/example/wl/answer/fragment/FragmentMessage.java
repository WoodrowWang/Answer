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
import android.widget.TextView;
import android.widget.Toast;

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
        MyAdapter adapter = new MyAdapter();
        messageRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext,"click "+ position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ChatActivity.class);
                startActivity(intent);
            }
        });
        for (int i = 0; i < 10; i++) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setName("name" + i);
            messageInfo.setTime(i + "" + i + ":" + i + "" + i);
            messageInfo.setContent("content" + i + "hakjhafkajdhkjhfanvduvhaeuiavdjavjkd");
            messageInfoList.add(messageInfo);
        }
        return view;
    }

    private interface OnItemClickListener{
        void onItemClick(int position);
    }

    private class MyAdapter extends RecyclerView.Adapter {

        private OnItemClickListener onItemClickListener;

        private void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
            return new MessageInfoViewHolder(v);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final MessageInfoViewHolder mHolder = (MessageInfoViewHolder) holder;
            mHolder.messageName.setText(messageInfoList.get(position).getName());
            mHolder.messageTime.setText(messageInfoList.get(position).getTime());
            mHolder.messageContent.setText(messageInfoList.get(position).getContent());
            mHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(mHolder.getLayoutPosition());
                }
            });
        }

        @Override
        public int getItemCount() {
            return messageInfoList.size();
        }

        private class MessageInfoViewHolder extends RecyclerView.ViewHolder {
            private TextView messageName, messageTime, messageContent;

            private MessageInfoViewHolder(View itemView) {
                super(itemView);
                messageName = (TextView) itemView.findViewById(R.id.message_name);
                messageTime = (TextView) itemView.findViewById(R.id.message_time);
                messageContent = (TextView) itemView.findViewById(R.id.message_content);
            }
        }
    }
}
