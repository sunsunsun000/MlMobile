package ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zw.mlmobile.R;

import base.BaseActivity;
import base.SocketModule;
import dao.MobileUser;
import ui.view.NoConnectDialog;
import util.MlConCommonUtil;
import util.MlConnectUtil;

/**
 * Created by zhongwang on 2018/1/9.
 */

public class LoginActivity extends BaseActivity {
    private static final String TAG = "MLTAG";
    private EditText edUserName;
    private EditText edPassword;
    private Button btLogin;
    private Button btCancel;
    private NoConnectDialog noConnectDialog;
    private String username, password;

    private SocketModule socketModule;

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
                if (isEmpty(edUserName) || isEmpty(edPassword)) {
                    showToast(R.string.nousername);
                    return;
                } else {
                    username = getString(edUserName);
                    password = getString(edPassword);
                }
sendDataToMotor();
                break;
            case R.id.btCancel:
                finish();
                break;
        }
    }

    private void sendDataToMotor() {
        if (socketModule == null)
            socketModule = new SocketModule();
        socketModule.setOperateType(MlConCommonUtil.LOGIN);
        socketModule.setBaseModule(gson.toJson(new MobileUser(username, password)));
         mlConnectUtil.getDataFromMotor(socketModule, new MlConnectUtil.OperateData() {
            @Override
            public void handlerData(SocketModule socketModule) {
            }
        });
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
