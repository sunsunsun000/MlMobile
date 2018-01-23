package base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by zhongwang on 2018/1/23.
 */

public abstract class BaseFragment extends Fragment {
    public BaseActivity baseActivity;
    public View view;
    private MlApplication mlApplication;

    public BaseFragment() {
    }

    public void showToast(String content) {
        baseActivity.showToast(content);
    }
    public void showToast(int resId) {
        baseActivity.showToast(resId);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = initView(inflater);
        initFindViewById(view);
        return view;
    }

    public View getRootView() {
        return view;
    }

    //子类复写此方法初始化事件
    protected void initEvent() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();
        mlApplication = (MlApplication) baseActivity.getApplication();
        initData(savedInstanceState);
        initEvent();
    }


    public abstract View initView(LayoutInflater inflater);

    public abstract void initFindViewById(View view);

    public abstract void initData(@Nullable Bundle savedInstanceState);
}
