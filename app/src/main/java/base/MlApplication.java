package base;

import android.app.Application;

import com.google.gson.Gson;

import util.MlConnectUtil;

/**
 * Created by zhongwang on 2018/1/9.
 */

public class MlApplication extends Application {
    private boolean isConnected = false;
    private Gson gson;
    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initLib();
        // TODO: 2018/1/9 通过wifi链接机器
        initMlConnect();
    }

    private void initMlConnect() {
        MlConnectUtil.ConnectServer("192.168.3.135",this);
    }
    private void initLib() {
        gson = new Gson();
    }
}
