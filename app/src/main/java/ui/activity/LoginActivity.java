package ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zw.mlmobile.R;

import base.BaseActivity;
import base.MlApplication;
import ui.view.NoConnectDialog;

/**
 * Created by zhongwang on 2018/1/9.
 */

public class LoginActivity extends BaseActivity {
    private static final String TAG = "MLTAG";
    private EditText edUserName;
    private EditText edPassword;
    private Button btLogin;
    private Button btCancel;
    private MlApplication mlApplication;
    private NoConnectDialog noConnectDialog;

    @Override
    public int getLayoutId() {
        setFullScreen();
        return R.layout.activity_login;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        mlApplication = (MlApplication) getApplication();
    }

    @Override
    protected void initView() {
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btLogin = findViewById(R.id.btLogin);
        btCancel = findViewById(R.id.btCancel);
    }

    @Override
    protected void setListener() {
        btLogin.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                //如果没有通过socket链接到机器
                if (!mlApplication.isConnected()) {
                    showNoConnectDialog();
                    return;
                }
                Log.d(TAG, "server connect");
                // TODO: 2018/1/9 连接相关 
                break;
            case R.id.btCancel:
                break;
        }
    }

    /**
     * 展示通信未连接对话框
     */
    private void showNoConnectDialog() {
        if (noConnectDialog == null)
            noConnectDialog = new NoConnectDialog(this);
        noConnectDialog.show();
    }
}
