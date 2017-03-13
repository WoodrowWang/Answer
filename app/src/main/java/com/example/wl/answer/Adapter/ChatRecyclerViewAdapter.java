package com.example.wl.answer.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wl.answer.R;
import com.example.wl.answer.model.ChatText;

import java.util.ArrayList;

/**
 * Created by wanglin on 17-3-13.
 */

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<ChatText> messageList;

    public ChatRecyclerViewAdapter(ArrayList<ChatText> messageList) {
        this.messageList = messageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ChatText.TYPE_OWN) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_own, parent, false);
            return new OwnViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_other, parent, false);
            return new OtherViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OwnViewHolder) {
            OwnViewHolder viewHolder = (OwnViewHolder) holder;
            viewHolder.textView.setText(messageList.get(position).getText());
        } else {
            OtherViewHolder viewHolder = (OtherViewHolder) holder;
            viewHolder.textView.setText(messageList.get(position).getText());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if(messageList.get(position).getType() == ChatText.TYPE_OTHER){
//            return ChatText.TYPE_OTHER;
//        }else{
//            return ChatText.TYPE_OWN;
//        }

        if ((position + 1) % 2 == 0) {
            return ChatText.TYPE_OWN;
        } else
            return ChatText.TYPE_OTHER;
    }

    private class OwnViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView headIv;

        public OwnViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.chat_text_own);
            headIv = (ImageView) itemView.findViewById(R.id.chat_head_own);
        }
    }

    private class OtherViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView headIv;

        public OtherViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.chat_text_other);
            headIv = (ImageView) itemView.findViewById(R.id.chat_head_other);
        }
    }

}
