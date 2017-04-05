package com.example.wl.answer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wl.answer.R;
import com.example.wl.answer.model.ChatText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wanglin on 17-3-13.
 */

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<ChatText> mChatTexts;
    private boolean isContextMenuShow;
//    private OnItemTouchListener mItemTouchListener;

    public ChatRecyclerViewAdapter(ArrayList<ChatText> chatTexts) {
        mChatTexts = chatTexts;
        isContextMenuShow = false;
    }

//    public interface OnItemTouchListener {
//        void onItemLongClick(int position);
//
//        void onItemTouch();
//
//        void onCreateContextMenu(ContextMenu menu,int position);
//    }

    public void setContextMenuShow(boolean contextMenuShow) {
        isContextMenuShow = contextMenuShow;
    }

    public boolean isContextMenuShow() {
        return isContextMenuShow;
    }

//    public void setItemTouchListener(OnItemTouchListener itemTouchListener) {
//        this.mItemTouchListener = itemTouchListener;
//    }

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
            final OwnViewHolder viewHolder = (OwnViewHolder) holder;
            viewHolder.textView.setText(mChatTexts.get(position).getText());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.CHINA);
            viewHolder.timeTv.setText(format.format(new Date(mChatTexts.get(position).getDate())));
            if (isContextMenuShow){
                viewHolder.mCheckBox.setVisibility(View.VISIBLE);
                viewHolder.mCheckBox.setChecked(false);
                viewHolder.textView.setClickable(false);
                viewHolder.textView.setLongClickable(false);
                viewHolder.textView.setFocusable(false);
//                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewHolder.mCheckBox.setChecked(!viewHolder.mCheckBox.isChecked());
//                        mCheckeds.add(viewHolder.getLayoutPosition(),!viewHolder.mCheckBox.isChecked());
//                    }
//                });
            }else{
                viewHolder.mCheckBox.setVisibility(View.GONE);
//                viewHolder.textView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//                    @Override
//                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//                        mItemTouchListener.onCreateContextMenu(menu,viewHolder.getLayoutPosition());
//                    }
//                });
            }

//            viewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    mItemTouchListener.onItemLongClick(holder.getLayoutPosition());
//                    return true;
//
//                }
//            });
        } else {
            final OtherViewHolder viewHolder = (OtherViewHolder) holder;
            viewHolder.textView.setText(mChatTexts.get(position).getText());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.CHINA);
            viewHolder.timeTv.setText(format.format(new Date(mChatTexts.get(position).getDate())));
            if (isContextMenuShow){
                viewHolder.mCheckBox.setVisibility(View.VISIBLE);
                viewHolder.mCheckBox.setChecked(false);
                viewHolder.textView.setClickable(false);
                viewHolder.textView.setLongClickable(false);
                viewHolder.textView.setFocusable(false);
//                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewHolder.mCheckBox.setChecked(!viewHolder.mCheckBox.isChecked());
//                        mCheckeds.add(viewHolder.getLayoutPosition(),!viewHolder.mCheckBox.isChecked());
//                    }
//                });
            }else{
                viewHolder.mCheckBox.setVisibility(View.GONE);
//                viewHolder.textView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//                    @Override
//                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//                        mItemTouchListener.onCreateContextMenu(menu,viewHolder.getLayoutPosition());
//                    }
//                });
            }

//            viewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    mItemTouchListener.onItemLongClick(holder.getLayoutPosition());
//                    return true;
//                }
//            });
        }

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                mItemTouchListener.onItemLongClick(holder.getLayoutPosition());
//                return true;
//            }
//        });
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

    private class OwnViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, timeTv;
        private ImageView headIv;
        private CheckBox mCheckBox;

        private OwnViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.chat_text);
            headIv = (ImageView) itemView.findViewById(R.id.chat_head_own);
            timeTv = (TextView) itemView.findViewById(R.id.chat_time_own);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.chat_check);
        }
    }

    private class OtherViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, timeTv;
        private ImageView headIv;
        private CheckBox mCheckBox;

        private OtherViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.chat_text);
            headIv = (ImageView) itemView.findViewById(R.id.chat_head_other);
            timeTv = (TextView) itemView.findViewById(R.id.chat_time_other);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.chat_check);
        }
    }

    //    private View.OnLongClickListener mLongClickListener = new View.OnLongClickListener() {
//        @Override
//        public boolean onLongClick(View v) {
//            mItemTouchListener.onItemLongClick();
//            return true;
//        }
//    };

}
