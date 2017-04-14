package com.example.wl.answer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wl.answer.R;
import com.example.wl.answer.listener.RVItemClickListener;
import com.example.wl.answer.model.MessageInfo;

import java.util.ArrayList;

/**
 * Created by wanglin on 17-3-15.
 */

public class MessageAdapter extends RecyclerView.Adapter{

    private RVItemClickListener mItemClickListener;
    private ArrayList<MessageInfo> messageInfos;

    public MessageAdapter(ArrayList<MessageInfo> messageInfos) {
        this.messageInfos = messageInfos;
    }

    public void setItemClickListener(RVItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageInfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MessageInfoViewHolder mHolder = (MessageInfoViewHolder) holder;
        mHolder.messageName.setText(messageInfos.get(position).getName());
        mHolder.messageTime.setText(messageInfos.get(position).getTime());
        mHolder.messageContent.setText(messageInfos.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return messageInfos.size();
    }

    private class MessageInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView messageName, messageTime, messageContent;

        private MessageInfoViewHolder(View itemView) {
            super(itemView);
            messageName = (TextView) itemView.findViewById(R.id.message_name);
            messageTime = (TextView) itemView.findViewById(R.id.message_time);
            messageContent = (TextView) itemView.findViewById(R.id.message_content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener!=null){
                mItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}

