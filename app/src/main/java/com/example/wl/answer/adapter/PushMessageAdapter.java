package com.example.wl.answer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wl.answer.R;
import com.example.wl.answer.listener.RVItemClickListener;
import com.example.wl.answer.model.PushMessage;

import java.util.ArrayList;

/**
 * Created by wanglin on 17-4-6.
 */

public class PushMessageAdapter extends RecyclerView.Adapter {
    private ArrayList<PushMessage> mPushMessages;
    private RVItemClickListener mItemClickListener;

    public PushMessageAdapter(ArrayList<PushMessage> pushMessages) {
        mPushMessages = pushMessages;
    }

    public void setItemClickListener(RVItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pushmessage,parent,false);
        return new PushMessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PushMessage pushMessage = mPushMessages.get(position);
        ((PushMessageViewHolder)holder).mTitleTv.setText(pushMessage.getTitle());
        ((PushMessageViewHolder)holder).mDetailTv.setText(pushMessage.getDetails());
    }

    @Override
    public int getItemCount() {
        return mPushMessages != null ? mPushMessages.size() : 0;
    }

    private class PushMessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTv, mDetailTv;

        public PushMessageViewHolder(View itemView) {
            super(itemView);
            mTitleTv = (TextView) itemView.findViewById(R.id.pushmessage_title);
            mDetailTv = (TextView) itemView.findViewById(R.id.pushmessage_detail);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
