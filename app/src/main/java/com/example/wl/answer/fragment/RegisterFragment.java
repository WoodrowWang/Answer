package com.example.wl.answer.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wl.answer.R;
import com.example.wl.answer.activity.LoginActivity;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class RegisterFragment extends BaseFragment implements View.OnClickListener{
    @Override
    protected int getResId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void init(View view, Context context) {
        TextView gotoLogin = (TextView) view.findViewById(R.id.goto_login);
        Button registerBt = (Button) view.findViewById(R.id.button_register);
        EditText idEt = (EditText)  view.findViewById(R.id.register_edit_id);
        EditText pwdEt = (EditText)  view.findViewById(R.id.register_edit_pwd);
        EditText pwdConfirmEt = (EditText)  view.findViewById(R.id.register_edit_pwd_confirm);

        gotoLogin.setOnClickListener(this);
        registerBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goto_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.button_register:
                getActivity().finish();
                break;
            default:
                break;
        }
    }
}
