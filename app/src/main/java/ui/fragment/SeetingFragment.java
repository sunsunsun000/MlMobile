package ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.example.zw.mlmobile.R;

import base.BaseFragment;

/**
 * Created by zhongwang on 2018/1/23.
 */

public class SeetingFragment extends BaseFragment {
    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_seeting,null);
    }

    @Override
    public void initFindViewById(View view) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
