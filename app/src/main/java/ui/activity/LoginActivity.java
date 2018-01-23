package ui.activity;

import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zw.mlmobile.R;

import java.io.IOException;

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
    private Boolean isLogined= false;

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
        progressDialog.setTitle(R.string.loading);
        progressDialog.show();
        mlConnectUtil.getDataFromMotor(socketModule, new MlConnectUtil.OperateData() {
            @Override
            public void handlerData(SocketModule socketModule) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                isLogined = gson.fromJson(socketModule.getBaseModule(), Boolean.class);
                if (isLogined) {
                    enterActivityAndKillSelf(HomeActivity.class);
                } else {
                    showToast(R.string.loginfailure);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //判断是否已经登陆，如果未登陆则断开与服务器连接
        if(!isLogined){
            Log.d("zww","应用程序退出中，暂未登陆成功，断开与服务器所有连接");
            printWriter.close();
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mlConnectUtil.closeSocket();
            android.os.Process.killProcess(android.os.Process.myPid());    //获取PID
            System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出



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
