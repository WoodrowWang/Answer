package com.example.wl.answer.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wl.answer.R;
import com.example.wl.answer.listener.RVItemCheckListener;
import com.example.wl.answer.listener.RVItemLongClickListener;
import com.example.wl.answer.model.ChatText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wanglin on 17-3-13.
 */

public class ChatLogAdapter extends RecyclerView.Adapter {
    private static final String TAG = "===============>";
    private ArrayList<ChatText> mChatTexts;
    private boolean isCheckable;
    private RVItemLongClickListener mLongClickListener;
    private RVItemCheckListener mCheckListener;

    public ChatLogAdapter(ArrayList<ChatText> chatTexts) {
        mChatTexts = chatTexts;
        isCheckable = false;
    }

    public void setLongClickListener(RVItemLongClickListener longClickListener) {
        mLongClickListener = longClickListener;
    }

    public void setCheckListener(RVItemCheckListener checkListener) {
        mCheckListener = checkListener;
    }

    public void setCheckable(boolean checkable) {
        isCheckable = checkable;
    }

    public boolean isCheckable() {
        return isCheckable;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
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
        Log.i(TAG, "onBindViewHolder: "+position);
        if (holder instanceof OwnViewHolder) {
            final OwnViewHolder viewHolder = (OwnViewHolder) holder;
            viewHolder.textView.setText(mChatTexts.get(position).getText());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.CHINA);
            viewHolder.timeTv.setText(format.format(new Date(mChatTexts.get(position).getDate())));
            if (isCheckable){
                viewHolder.mCheckBox.setVisibility(View.VISIBLE);
                viewHolder.mCheckBox.setChecked(false);
            }else{
                viewHolder.mCheckBox.setVisibility(View.GONE);
            }
        } else {
            final OtherViewHolder viewHolder = (OtherViewHolder) holder;
            viewHolder.textView.setText(mChatTexts.get(position).getText());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.CHINA);
            viewHolder.timeTv.setText(format.format(new Date(mChatTexts.get(position).getDate())));
            if (isCheckable){
                viewHolder.mCheckBox.setVisibility(View.VISIBLE);
                viewHolder.mCheckBox.setChecked(false);
            }else{
                viewHolder.mCheckBox.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mChatTexts.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if(mChatTexts.get(position).getType() == ChatText.TYPE_OTHER){
//            return ChatText.TYPE_OTHER;
//        }else{
//            return ChatText.TYPE_OWN;
//        }

        if ((position + 1) % 2 == 0) {
            return ChatText.TYPE_OWN;
        } else
            return ChatText.TYPE_OTHER;
    }

    private class OwnViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView textView, timeTv;
        private ImageView headIv;
        private CheckBox mCheckBox;

        private OwnViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.chat_text);
            headIv = (ImageView) itemView.findViewById(R.id.chat_head_own);
            timeTv = (TextView) itemView.findViewById(R.id.chat_time_own);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.chat_check);

            textView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            Log.i(TAG, "OwnViewHolder: ");
        }

        @Override
        public void onClick(View v) {
            Log.i(TAG, "onClick: own" + " ischecked: " + isCheckable());
            if (isCheckable && mCheckListener != null) {
                mCheckBox.setChecked(!mCheckBox.isChecked());
                mCheckListener.onItemChecked(getAdapterPosition(),mCheckBox.isChecked());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            Log.i(TAG, "onLongClick: own");
            if (!isCheckable &&mLongClickListener!=null){
                mLongClickListener.onItemLongClick(getAdapterPosition());
            }

            return true;
        }
    }

    private class OtherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView textView, timeTv;
        private ImageView headIv;
        private CheckBox mCheckBox;

        private OtherViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.chat_text);
            headIv = (ImageView) itemView.findViewById(R.id.chat_head_other);
            timeTv = (TextView) itemView.findViewById(R.id.chat_time_other);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.chat_check);

            textView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            Log.i(TAG, "OtherViewHolder: ");
        }

        @Override
        public void onClick(View v) {
            Log.i(TAG, "onClick: other");
            if (isCheckable && mCheckListener != null) {
                mCheckBox.setChecked(!mCheckBox.isChecked());
                mCheckListener.onItemChecked(getAdapterPosition(),mCheckBox.isChecked());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            Log.i(TAG, "onLongClick: other");
            if (!isCheckable &&mLongClickListener!=null){
                mLongClickListener.onItemLongClick(getAdapterPosition());
            }

            return true;
        }
    }

}
