package ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zw.mlmobile.R;

import java.util.ArrayList;

import base.BaseActivity;
import ui.fragment.SplashFragment;
import util.CommonUtil;
import util.SharedPreferencesUtil;

/**
 * Created by zhongwang on 2018/1/9.
 */

public class SplashActivity extends BaseActivity {
    private ViewPager vp;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private Button btStart;
    private ArrayList<Fragment> fragments;
    private boolean isFirst;

    @Override
    public int getLayoutId() {
        setFullScreen();
        return R.layout.activity_splash;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        Fragment fragment1 = new SplashFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("index", 1);
        fragment1.setArguments(bundle1);
        fragments.add(fragment1);
        Fragment fragment2 = new SplashFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("index", 2);
        fragment2.setArguments(bundle2);
        fragments.add(fragment2);
        Fragment fragment3 = new SplashFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("index", 3);
        fragment3.setArguments(bundle3);
        fragments.add(fragment3);
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        vp.addOnPageChangeListener(new MyPageChangeListener());
    }

    @Override
    protected void initView() {
        isFirst = (boolean) SharedPreferencesUtil.getParam(this, CommonUtil.FIRSTTAG, true);
        if (!isFirst) {
            enterActivity(LoginActivity.class);
            finish();
        }
        SharedPreferencesUtil.setParam(this, CommonUtil.FIRSTTAG, false);

        vp = findViewById(R.id.vp);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        btStart = findViewById(R.id.bt_start);
    }

    @Override
    protected void setListener() {
        btStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                enterActivity(LoginActivity.class);
                finish();
                break;
        }
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 根据页面不同动态改变红点和在最后一页显示立即体验按钮
         *
         * @param position
         */
        @Override
        public void onPageSelected(int position) {
            btStart.setVisibility(View.GONE);
            iv1.setImageResource(R.mipmap.dot_normal);
            iv2.setImageResource(R.mipmap.dot_normal);
            iv3.setImageResource(R.mipmap.dot_normal);
            if (position == 0) {
                iv1.setImageResource(R.mipmap.dot_focus);
            } else if (position == 1) {
                iv2.setImageResource(R.mipmap.dot_focus);
            } else {
                iv3.setImageResource(R.mipmap.dot_focus);
                btStart.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * ViewPager适配器
     */
    private class MyPageAdapter extends FragmentPagerAdapter {


        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
