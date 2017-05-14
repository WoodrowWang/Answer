package com.example.wl.answer.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.wl.answer.R;
import com.example.wl.answer.activity.LoginActivity;
import com.example.wl.answer.activity.MainActivity;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class MoreFragment extends BaseFragment {
    private Context mContext;
    private View mNoLoginView;
    private TextView mUserNameTv;
    private boolean isLogin = false;
    private String mUserName = "";
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if (savedInstanceState != null) {
//            mUserName = savedInstanceState.getString("name");
//            Log.i(TAG, "onActivityCreated: name == "+mUserName);
//        }
//    }

    @Override
    protected int getResId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void init(View view, Context context) {
        mContext = context;
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.isLogin();
        mNoLoginView = view.findViewById(R.id.more_no_login);
        mUserNameTv = (TextView) view.findViewById(R.id.more_username);
        if (isLogin) {
            mNoLoginView.setVisibility(View.GONE);
            mUserNameTv.setText(mUserName);
        }
        TextView gotoLogin = (TextView) view.findViewById(R.id.more_to_login);
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 2017);
            }
        });

    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putBoolean("isLogin",isLogin);
//        outState.putString("name",mUserName);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 2017) {
            isLogin = true;
            mUserName = data.getStringExtra("name");
            mNoLoginView.setVisibility(View.GONE);
            mUserNameTv.setText(mUserName);
        }
    }
}
