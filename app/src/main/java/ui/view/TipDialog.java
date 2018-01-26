package ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.zw.mlmobile.R;

import base.BaseDialog;

/**
 * Created by zhongwang on 2018/1/9.
 */

public class TipDialog extends BaseDialog {
    private TextView tvContent;
    private responseOperate responseOperate;

    public void setResponseOperate(TipDialog.responseOperate responseOperate) {
        this.responseOperate = responseOperate;
    }

    public TipDialog(@NonNull Context context) {
        super(context, R.layout.dialog_tip, new int[]{R.id.tvPostive, R.id.tvNegative});
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvContent = findViewById(R.id.tvcontent);
    }

    public void setContent(String content) {
        tvContent.setText(content);
    }

    @Override
    protected void setDataToView() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClick(View view) {
        dismiss();
        if(responseOperate!=null)
        switch (view.getId()) {
            case R.id.tvPostive:
                      responseOperate.postive();
                break;
            case R.id.tvNegative:
                 responseOperate.negative();
                break;
        }
    }

    public interface responseOperate {
        void postive();

        void negative();
    }
}
