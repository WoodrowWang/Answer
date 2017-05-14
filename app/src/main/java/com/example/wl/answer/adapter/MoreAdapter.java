//package com.example.wl.answer.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.wl.answer.R;
//import com.example.wl.answer.listener.RVItemClickListener;
//
//import static android.content.ContentValues.TAG;
//
///**
// * Created by Administrator on 2017/5/9 0009.
// */
//
//public class MoreAdapter extends RecyclerView.Adapter{
//
//    private String mUserName = "";
//    private boolean mLoginFlag = false;
//    private RVItemClickListener mItemClickListener;
//
//    public void setItemClickListener(RVItemClickListener itemClickListener) {
//        mItemClickListener = itemClickListener;
//    }
//
//    public void setLoginFlag(boolean loginFlag) {
//        mLoginFlag = loginFlag;
//    }
//
//    public void setUserName(String userName) {
//        mUserName = userName;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == 0){
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_more_head_logined, parent, false);
//            return new LoginedHeadViewHolder(v);
//        }else {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_more_head_nologin, parent, false);
//            return new NoLoginHeadViewHolder(v);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        Log.i(TAG, "onBindViewHolder: mLoginFlag====" + mLoginFlag);
//        if (mLoginFlag&&position==1) {
//            holder.itemView.setVisibility(View.GONE);
//        }else if (mLoginFlag&&position == 0){
//            ((LoginedHeadViewHolder) holder).mUserNameTv.setText(mUserName);
//        }else if (!mLoginFlag&&position==0){
//            holder.itemView.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//    private class LoginedHeadViewHolder extends RecyclerView.ViewHolder{
//        private TextView mUserNameTv;
//        private ImageView mHeadIv;
//        public LoginedHeadViewHolder(View itemView) {
//            super(itemView);
//            mUserNameTv = (TextView) itemView.findViewById(R.id.more_username);
//            mHeadIv = (ImageView)  itemView.findViewById(R.id.more_head);
//        }
//    }
//
//    private class NoLoginHeadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        private TextView toLoginTv;
//        public NoLoginHeadViewHolder(View itemView) {
//            super(itemView);
//            toLoginTv = (TextView) itemView.findViewById(R.id.more_to_login);
//            toLoginTv.setOnClickListener(this);
//        }
//        @Override
//        public void onClick(View v) {
//            mItemClickListener.onItemClick(0);
//        }
//    }
//
//}
