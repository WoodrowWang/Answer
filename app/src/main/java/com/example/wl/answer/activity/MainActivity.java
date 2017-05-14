package com.example.wl.answer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.wl.answer.R;
import com.example.wl.answer.fragment.MessageFragment;
import com.example.wl.answer.fragment.MoreFragment;
import com.example.wl.answer.fragment.NotificationFragment;
import com.example.wl.answer.fragment.PushMessageFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PUSH_MESSAGE_FLAG = 0;
    private static final int CHAT_MESSAGE_FLAG = 1;
    private static final int NOTIFICATION_FLAG = 2;
    private static final int MORE_FLAG = 3;

    private ActionBar mActionBar;
//    private MyViewPager mViewPager;
    private TextView[] mBottomBts;
    private Fragment[] mFragments;
    private int mLastSelectedFlag = PUSH_MESSAGE_FLAG;
    private boolean isLogin = false;
    private PushMessageFragment mPushMessageFragment;
    private MessageFragment mMessageFragment;
    private NotificationFragment mNotificationFragment;
    private MoreFragment mMoreFragment;
    private FragmentManager mFragmentManager;

    public boolean isLogin() {
        return isLogin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionBar = getSupportActionBar();
        mActionBar.setTitle("资讯");

        TextView bottomNews = (TextView) findViewById(R.id.bottom_news);
        bottomNews.setOnClickListener(this);
        TextView bottomMsg = (TextView) findViewById(R.id.bottom_message);
        bottomMsg.setOnClickListener(this);
        TextView bottomNotification = (TextView) findViewById(R.id.bottom_notification);
        bottomNotification.setOnClickListener(this);
        TextView bottomMore = (TextView) findViewById(R.id.bottom_more);
        bottomMore.setOnClickListener(this);
        mBottomBts = new TextView[]{bottomNews, bottomMsg, bottomNotification, bottomMore};
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        mPushMessageFragment = new PushMessageFragment();
        mMessageFragment = new MessageFragment();
        mNotificationFragment = new NotificationFragment();
        mMoreFragment = new MoreFragment();

        mFragments = new Fragment[]{
                mPushMessageFragment,
                mMessageFragment,
                mNotificationFragment,
                mMoreFragment
        };

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.fragment_container_main, mNotificationFragment)
                .add(R.id.fragment_container_main, mMoreFragment)
                .add(R.id.fragment_container_main, mMessageFragment)
                .add(R.id.fragment_container_main, mPushMessageFragment)
                .hide(mMoreFragment)
                .hide(mNotificationFragment)
                .hide(mMessageFragment)
                .commit();

//        mViewPager = (MyViewPager) findViewById(R.id.viewpager_fragment);
//        mViewPager.setPagingEnabled(false);
//        mViewPager.setAdapter(new FragmentStatePagerAdapter(mFragmentManager) {
//            @Override
//            public Fragment getItem(int position) {
//                Fragment fragment = null;
//                switch (position) {
//                    case 0:
//                        fragment = mPushMessageFragment;
//                        break;
//                    case 1:
//                        fragment = mMessageFragment;
//                        break;
//                    case 2:
//                        fragment = mNotificationFragment;
//                        break;
//                    case 3:
//                        fragment = mMoreFragment;
//                        break;
//                }
//                return fragment;
//            }
//
//            @Override
//            public int getCount() {
//                return 4;
//            }
//        });
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mActionBar.setTitle(mBottomBts[position].getText());
//                setSelected(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public void onClick(View v) {
        int slecteFlag = 0;
        switch (v.getId()) {
            case R.id.bottom_news:
                slecteFlag = PUSH_MESSAGE_FLAG;
//                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.bottom_message:
                slecteFlag = CHAT_MESSAGE_FLAG;
//                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.bottom_notification:
                slecteFlag = NOTIFICATION_FLAG;
//                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.bottom_more:
                slecteFlag = MORE_FLAG;
//                mViewPager.setCurrentItem(3, false);
                break;
        }
        setSelected(slecteFlag);
    }

    private void setSelected(int selected) {
        if (selected != mLastSelectedFlag) {
            mFragmentManager.beginTransaction()
                    .show(mFragments[selected])
                    .hide(mFragments[mLastSelectedFlag])
                    .commit();
            mBottomBts[selected].setTextColor(getResources().getColor(R.color.colorPrimary));
            mBottomBts[mLastSelectedFlag].setTextColor(getResources().getColor(R.color.colorBottomText));
            mActionBar.setTitle(mBottomBts[selected].getText());
            mLastSelectedFlag = selected;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
