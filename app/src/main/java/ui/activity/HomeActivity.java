package ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import com.example.zw.mlmobile.R;

import base.BaseActivity;
import base.BaseFragment;
import ui.fragment.CalibrationFragment;
import ui.fragment.CheckFragment;
import ui.fragment.SearchFragment;
import ui.fragment.SeetingFragment;

/**
 * Created by zhongwang on 2018/1/23.
 */

public class HomeActivity extends BaseActivity {
    //定义四个子Fragment
    private CalibrationFragment calibrationFragment;
    private CheckFragment checkFragment;
    private SearchFragment searchFragment;
    private SeetingFragment seetingFragment;
    //定义底部导航栏点击响应事件
    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.calibration:
                    if (calibrationFragment == null)
                        calibrationFragment = new CalibrationFragment();
                    switchFragment(calibrationFragment);
                    return true;
                case R.id.check:
                    if(checkFragment==null)
                        checkFragment = new CheckFragment();
                    switchFragment(checkFragment);
                    return true;
                case R.id.search:
                    if(searchFragment==null)
                        searchFragment = new SearchFragment();
                    switchFragment(searchFragment);
                    return true;
                case R.id.seeting:
                    if(seetingFragment==null)
                        seetingFragment = new SeetingFragment();
                    switchFragment(seetingFragment);
                    return true;
                default:
                    return false;
            }

        }
    };
    private FragmentTransaction mfragmentTransaction;
    private BaseFragment mCurrentFrgment;
    private BottomNavigationView navigation;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        navigation.setSelectedItemId(R.id.calibration);
    }

    @Override
    protected void initView() {
        navigation = findViewById(R.id.navigation);//绑定界面中的导航条

    }

    @Override
    protected void setListener() {
        navigation.setOnNavigationItemSelectedListener(itemSelectedListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void switchFragment(Fragment fragment) {
        mfragmentTransaction = getFragmentManager().beginTransaction();
        if (null != mCurrentFrgment) {
            mfragmentTransaction.hide(mCurrentFrgment);
        }
        if (!fragment.isAdded()) {
            mfragmentTransaction.add(R.id.content, fragment, fragment.getClass().getName());
        } else {
            mfragmentTransaction.show(fragment);
        }
        mfragmentTransaction.commit();
        mCurrentFrgment = (BaseFragment) fragment;
    }

    @Override
    public void onClick(View view) {
    }
}
