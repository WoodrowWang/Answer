//package com.example.wl.answer.listener;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.CheckBox;
//import android.widget.TextView;
//
//import com.example.wl.answer.R;
//import com.example.wl.answer.adapter.ChatLogAdapter;
//
//import static android.content.ContentValues.TAG;
//
///**
// * Created by wanglin on 17-3-30.
// */
//
//public class RVItemTouchListener implements RecyclerView.OnItemTouchListener {
//
//    private RVItemClickListener mClickListener;
//    private RVItemLongClickListener mLongClickListener;
//    private RVItemCheckListener mCheckListener;
//    private RecyclerView mRecyclerView;
//    private GestureDetector mGestureDetector;
//    private ChatLogAdapter mAdapter;
//    private View mChildView;
//    private CheckBox mCheckBox;
//    private TextView mTextView;
//
//    public void setClickListener(RVItemClickListener clickListener) {
//        mClickListener = clickListener;
//    }
//
//    public void setLongClickListener(RVItemLongClickListener longClickListener) {
//        mLongClickListener = longClickListener;
//    }
//
//    public void setCheckListener(RVItemCheckListener checkListener) {
//        mCheckListener = checkListener;
//    }
//
//    public RVItemTouchListener(Context context) {
//        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//            @Override
//            public void onLongPress(MotionEvent e) {
//                Log.i(TAG, "onLongPress: ");
//                if (!mAdapter.isCheckable()){
//                    mChildView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
//                    if (mChildView != null && mLongClickListener != null) {
//                        int position = mRecyclerView.getChildAdapterPosition(mChildView);
//                        mLongClickListener.onItemLongClick(position);
//                    }
//                }
//            }
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                Log.i(TAG, "onSingleTapUp: ");
//                if (mAdapter.isCheckable()) {
//                    mChildView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
//                    if (mChildView != null && mCheckListener != null) {
//                        mCheckBox = (CheckBox) mChildView.findViewById(R.id.chat_check);
//                        mCheckBox.setChecked(!mCheckBox.isChecked());
//                        mCheckListener.onItemChecked(mRecyclerView.getChildAdapterPosition(mChildView), mCheckBox.isChecked());
//                    }
//                }
//                return true;
//            }
//
//            @Override
//            public boolean onDown(MotionEvent e) {
//                return super.onDown(e);
//            }
//        });
//
//
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//        if (mRecyclerView != rv) {
//            Log.i(TAG, "onInterceptTouchEvent: +++++++++++++++++");
//            mRecyclerView = rv;
//            mAdapter = (ChatLogAdapter) mRecyclerView.getAdapter();
//        }
//        mGestureDetector.onTouchEvent(e);
//        return false;
//    }
//
//    @Override
//    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//        Log.i(TAG, "onTouchEvent: +++++++++++++++++");
//
//    }
//
//    @Override
//    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//        Log.i(TAG, "onRequestDisallowInterceptTouchEvent: ++++++++++++++++++++++");
//    }
//}
