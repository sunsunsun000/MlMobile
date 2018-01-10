package ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.zw.mlmobile.R;

import base.BaseDialog;

/**
 * Created by zhongwang on 2018/1/9.
 */

public class NoConnectDialog extends BaseDialog {
    public NoConnectDialog(@NonNull Context context) {
        super(context, R.layout.dialog_noconnect, new int[]{R.id.tvPostive, R.id.tvNegative});
    }

    @Override
    protected void setDataToView() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvPostive:
                // TODO: 2018/1/9 进入设置IP界面
                break;
            case R.id.tvNegative:
                dismiss();
                break;
        }
    }
}
