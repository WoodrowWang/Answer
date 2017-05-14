package com.example.wl.answer.activity;

import android.support.v4.app.Fragment;

import com.example.wl.answer.fragment.LoginFragment;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class LoginActivity extends BaseFragmentActivity{
    @Override
    protected Fragment getFragment() {
        return new LoginFragment();
    }
}
