package base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.PrintWriter;

import util.MlConnectUtil;
import util.ToastUtil;

/**
 * 创建时间: 2017/11/29
 * 创建人: Administrator
 * 功能描述:基类Activity
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private int layoutId;
    private ToastUtil toastUtil;
    protected MlApplication mlApplication;
    protected Gson gson;
    protected MlConnectUtil mlConnectUtil;
    protected BufferedReader bufferedReader;
    protected PrintWriter printWriter;
    protected ProgressDialog progressDialog;
    /**
     * 设置界面布局Layout
     *
     * @return
     */
    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutId = getLayoutId();
        setContentView(layoutId);
        initBase();
        initView();
        setListener();
        initData();
        initEvent();
    }

    /**
     * 显示提示信息
     *
     * @param sourceId 资源Id
     */
    public void showToast(int sourceId) {
        toastUtil.showToast(getString(sourceId));
    }

    /**
     * 显示对话框
     *
     * @param content 显示内容
     */
    public void showToast(String content) {
        toastUtil.showToast(content);
    }

    /**
     * 绑定点击事件
     */
    protected abstract void initEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化View
     */
    protected abstract void initView();

    protected abstract void setListener();

    public void enterActivity(Class clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }
public void enterActivityAndKillSelf(Class clz){
    Intent intent = new Intent(this, clz);
    startActivity(intent);
    finish();
}
    /**
     * 设置全屏(必须在getLayoutId方法中调用)
     */
    public void setFullScreen() {
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
    }

    private void initBase() {
        toastUtil = ToastUtil.getInstance(this);
        mlApplication = (MlApplication) getApplication();
        gson = mlApplication.getGson();
        mlConnectUtil = mlApplication.getMlConnectUtil();
        printWriter = mlConnectUtil.getPrintWriter();
        bufferedReader = mlConnectUtil.getBufferedReader();
        progressDialog = new ProgressDialog(this);

    }

    public static boolean isEmpty(EditText editText) {
        return editText.getEditableText().toString().trim().equals("");
    }

    public static String getString(EditText editText) {
        return editText.getEditableText().toString();
    }
}



