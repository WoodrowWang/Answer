package com.example.wl.answer.fragment;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wl.answer.R;
import com.example.wl.answer.activity.RegisterActivity;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener{
    private String mUserName = "",mPwd = "";
    private Context mContext;
    @Override
    protected int getResId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void init(View view, Context context) {
        mContext = context;
        TextView gotoRegister = (TextView) view.findViewById(R.id.goto_register);
        Button loginBt = (Button) view.findViewById(R.id.button_login);
        EditText idEt = (EditText)  view.findViewById(R.id.login_edit_id);
        EditText pwdEt = (EditText)  view.findViewById(R.id.login_edit_pwd);

        idEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mUserName = s.toString();
            }
        });

        pwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPwd = s.toString();
            }
        });

        gotoRegister.setOnClickListener(this);
        loginBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goto_register:
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.button_login:
                if (loginConfirm(mUserName,mPwd)){
                    Intent data = new Intent();
                    data.putExtra("name",mUserName);
                    getActivity().setResult(2017,data);
                    getActivity().finish();
                }
                break;
            default:
                break;
        }
    }
    private boolean loginConfirm(String user, String pwd){
        return user.equals("123")&&pwd.equals("123");
    }
}
