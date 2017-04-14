package com.example.wl.answer.fragment;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wl.answer.R;
import com.example.wl.answer.adapter.PushMessageAdapter;
import com.example.wl.answer.model.PushMessage;

import java.util.ArrayList;

/**
 * Created by wanglin on 17-4-6.
 */

public class PushMessageFragment extends BaseFragment{
    private Context mContext;
    @Override
    protected void init(View view, Context context) {
        mContext = context;

        ArrayList<PushMessage> pushMessages = new ArrayList<>();
        for (int i = 0;i<10;i++){
            PushMessage pushMessage = new PushMessage("标题 "+i,"内容 "+i+"如需从被启动的activity获取返回结果,可使用与GeoQuiz应用中类似的实现代码。但本章我们无需这么做。本章我们将选择调用方法类似的实现代码类似的实现代码类似的实现代码。");
            pushMessages.add(pushMessage);
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        PushMessageAdapter adapter = new PushMessageAdapter(pushMessages);
        recyclerView.setAdapter(adapter);
    }
}
